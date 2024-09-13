package com.yond.blog.web.blog.view.controller;

import com.yond.blog.entity.CommentDO;
import com.yond.blog.entity.UserDO;
import com.yond.blog.service.CommentService;
import com.yond.blog.service.impl.UserServiceImpl;
import com.yond.blog.util.comment.CommentUtils;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.web.blog.view.req.CommentLeaveReq;
import com.yond.blog.web.blog.view.req.CommentPageViewReq;
import com.yond.blog.web.blog.view.vo.CommentViewVO;
import com.yond.common.annotation.AccessLimit;
import com.yond.common.constant.CommonConstant;
import com.yond.common.constant.JwtConstant;
import com.yond.common.enums.CommentOpenStateEnum;
import com.yond.common.enums.CommentPageEnum;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 评论
 * @Author: Naccl
 * @Date: 2020-08-15
 */
@RestController
@RequestMapping("/view/comment")
public class CommentController {
    
    @Resource
    private CommentService commentService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private CommentUtils commentUtils;
    
    @GetMapping("/page")
    public PageResponse<List<CommentViewVO>> comments(@RequestBody CommentPageViewReq req,
                                                      @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt) {
        CommentOpenStateEnum openState = commentService.getPageCommentStatus(req.getPage(), req.getBlogId());
        if (CommentOpenStateEnum.NOT_FOUND.equals(openState)) {
            return PageResponse.<List<CommentViewVO>>custom().setCode(404).setFailure("该博客不存在");
        }
        if (CommentOpenStateEnum.CLOSE.equals(openState)) {
            return PageResponse.<List<CommentViewVO>>custom().setCode(403).setFailure("评论已关闭");
        }
        if (CommentOpenStateEnum.PASSWORD.equals(openState)) {
            String err = this.verifyToken(jwt, req.getBlogId());
            if (StringUtils.isNotBlank(err)) {
                return PageResponse.<List<CommentViewVO>>custom().setCode(403).setFailure(err);
            }
        }
        Pair<Integer, List<CommentViewVO>> pair = commentService.viewPageBy(req.getPage(), req.getBlogId(), req.getPage(), req.getPageSize());
        return PageResponse.<List<CommentViewVO>>custom().setData(pair.getRight()).setTotal(pair.getLeft()).setSuccess();
    }
    
    
    @AccessLimit(seconds = 30, maxCount = 1, msg = "30秒内只能提交一次评论")
    @PostMapping("/leave")
    public Response<Boolean> postComment(@RequestBody CommentLeaveReq req,
                                         @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt,
                                         HttpServletRequest request) {
        
        Assert.hasText(req.getContent(), "评论为空");
        Assert.isTrue(req.getContent().length() <= 250, "评论过长");
        Assert.notNull(req.getPage(), "页面为空");
        Integer page = req.getPage();
        Long blogId = req.getBlogId();
        Long parentId = req.getParentId() == null ? CommonConstant.ROOT_ID : req.getParentId();
        boolean isBlogComment = CommentPageEnum.BLOG.getId().equals(page);
        boolean isVisitor = false;
        if (!isBlogComment) {
            blogId = null;
        }
        CommentDO parentComment = null;
        //对于有指定父评论的评论，应该以父评论为准，只判断页面可能会被绕过“评论开启状态检测”
        if (!Objects.equals(parentId, CommonConstant.ROOT_ID)) {
            //当前评论为子评论
            parentComment = commentService.getById(parentId);
            Assert.notNull(parentComment, "父评论为空：" + parentId);
            page = parentComment.getPage();
            if (isBlogComment) {
                blogId = parentComment.getBlogId();
            }
        }
        CommentOpenStateEnum openState = commentService.getPageCommentStatus(page, blogId);
        if (CommentOpenStateEnum.NOT_FOUND.equals(openState)) {
            return Response.custom(404, "该博客不存在");
        }
        if (CommentOpenStateEnum.CLOSE.equals(openState)) {
            return Response.custom(403, "评论已关闭");
        }
        if (CommentOpenStateEnum.PASSWORD.equals(openState)) {
        
        }
        if (CommentOpenStateEnum.OPEN.equals(openState)) {
        
        }
        switch (openState) {
            case PASSWORD:
                //文章受密码保护
                //验证Token合法性
                if (JwtUtil.judgeTokenIsExist(jwt)) {
                    String subject;
                    Claims claims;
                    try {
                        claims = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET);
                        subject = claims.getSubject();
                    } catch (Exception e) {
                        return Response.create(403, "Token已失效，请重新验证密码！");
                    }
                    //博主评论，不受密码保护限制，根据博主信息设置评论属性
                    if (subject.startsWith(JwtConstant.ADMIN_PREFIX)) {
                        //Token验证通过，获取Token中用户名
                        if (claims.getExpiration().before(new Date())) {
                            return Response.create(403, "博主身份Token已失效，请重新登录！");
                        }
                        UserDO admin = userService.getByGuid(subject.replace(JwtConstant.ADMIN_PREFIX, ""));
                        commentUtils.setAdminComment(comment, request, admin);
                    } else {//普通访客经文章密码验证后携带Token
                        //对访客的评论昵称、邮箱合法性校验
                        if (StringUtils.isBlank(comment.getNickname()) || StringUtils.isBlank(comment.getEmail()) || comment.getNickname().length() > 15) {
                            return Response.failure("参数有误");
                        }
                        //对于受密码保护的文章，则Token是必须的
                        Long tokenBlogId = Long.parseLong(subject);
                        //博客id不匹配，验证不通过，可能博客id改变或客户端传递了其它密码保护文章的Token
                        if (!tokenBlogId.equals(comment.getBlogId())) {
                            return Response.create(403, "Token不匹配，请重新验证密码！");
                        }
                        commentUtils.setVisitorComment(comment, request);
                        isVisitor = true;
                    }
                } else {//不存在Token则无评论权限
                    return Response.create(403, "此文章受密码保护，请验证密码！");
                }
                break;
            case OPEN:
                //评论正常开放
                //有Token则为博主评论，或文章原先为密码保护，后取消保护，但客户端仍存在Token
                if (JwtUtil.judgeTokenIsExist(jwt)) {
                    // todo 等修改
                    String subject;
                    try {
                        subject = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET).getSubject();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Response.create(403, "Token已失效，请重新验证密码");
                    }
                    //博主评论，根据博主信息设置评论属性
                    if (subject.startsWith(JwtConstant.ADMIN_PREFIX)) {
                        //Token验证通过，获取Token中用户名
                        String username = subject.replace(JwtConstant.ADMIN_PREFIX, "");
                        UserDO admin = userService.getByGuid(username);
                        if (admin == null) {
                            return Response.create(403, "博主身份Token已失效，请重新登录！");
                        }
                        commentUtils.setAdminComment(comment, request, admin);
                    } else {//文章原先为密码保护，后取消保护，但客户端仍存在Token，则忽略Token
                        //对访客的评论昵称、邮箱合法性校验
                        if (StringUtils.isBlank(comment.getNickname()) || StringUtils.isBlank(comment.getEmail()) || comment.getNickname().length() > 15) {
                            return Response.failure("参数有误");
                        }
                        commentUtils.setVisitorComment(comment, request);
                        isVisitor = true;
                    }
                } else {
                    //访客评论
                    //对访客的评论昵称、邮箱合法性校验
                    if (StringUtils.isBlank(comment.getNickname()) || StringUtils.isBlank(comment.getEmail()) || comment.getNickname().length() > 15) {
                        return Response.failure("参数有误");
                    }
                    commentUtils.setVisitorComment(comment, request);
                    isVisitor = true;
                }
                break;
            default:
                break;
        }
        commentService.saveComment(comment);
        commentUtils.judgeSendNotify(comment, isVisitor, parentComment);
        return Response.ok("评论成功");
    }
    
    
    private String verifyToken(String jwt, Long blogId) {
        boolean tokenIsExist = JwtUtil.judgeTokenIsExist(jwt);
        if (!tokenIsExist) {
            return "此文章受密码保护，请验证密码！";
        }
        try {
            Claims claims = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET);
            String subject = claims.getSubject();
            if (subject.startsWith(JwtConstant.ADMIN_PREFIX)) {
                if (claims.getExpiration().before(new Date())) {
                    return "博主身份Token已失效，请重新登录！";
                }
            } else {
                // 游客验证
                Long tokenBlogId = Long.parseLong(subject);
                //博客id不匹配，验证不通过，可能博客id改变或客户端传递了其它密码保护文章的Token
                if (!tokenBlogId.equals(blogId)) {
                    return "Token不匹配，请重新验证密码！";
                }
            }
        } catch (Exception e) {
            return "Token已失效，请重新验证密码！";
        }
        return null;
    }
    
}