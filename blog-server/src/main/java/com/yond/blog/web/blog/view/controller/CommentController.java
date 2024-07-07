package com.yond.blog.web.blog.view.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.CommentDO;
import com.yond.blog.entity.UserDO;
import com.yond.blog.service.CommentService;
import com.yond.blog.service.impl.UserServiceImpl;
import com.yond.blog.util.comment.CommentUtils;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.web.blog.view.dto.Comment;
import com.yond.blog.web.blog.view.vo.PageComment;
import com.yond.blog.web.blog.view.vo.PageResult;
import com.yond.common.annotation.AccessLimit;
import com.yond.common.constant.JwtConstant;
import com.yond.common.enums.CommentOpenStateEnum;
import com.yond.common.resp.Response;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 评论
 * @Author: Naccl
 * @Date: 2020-08-15
 */
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CommentUtils commentUtils;

    /**
     * 根据页面分页查询评论列表
     *
     * @param page     页面分类（0普通文章，1关于我...）
     * @param blogId   如果page==0，需要博客id参数
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @param jwt      若文章受密码保护，需要获取访问Token
     * @return
     */
    @GetMapping("/view/comments")
    public Response comments(@RequestParam Integer page,
                             @RequestParam(defaultValue = "") Long blogId,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt) {
        CommentOpenStateEnum openState = commentUtils.judgeCommentState(page, blogId);
        switch (openState) {
            case NOT_FOUND:
                return Response.custom(404, "该博客不存在");
            case CLOSE:
                return Response.custom(403, "评论已关闭");
            case PASSWORD:
                //文章受密码保护，需要验证Token
                if (JwtUtil.judgeTokenIsExist(jwt)) {
                    try {
                        Claims claims = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET);
                        String subject = claims.getSubject();
                        if (subject.startsWith(JwtConstant.ADMIN_PREFIX)) {
                            //博主身份Token
                            if (claims.getExpiration().before(new Date())) {
                                return Response.custom(403, "博主身份Token已失效，请重新登录！");
                            }
                        } else {
                            //经密码验证后的Token
                            Long tokenBlogId = Long.parseLong(subject);
                            //博客id不匹配，验证不通过，可能博客id改变或客户端传递了其它密码保护文章的Token
                            if (!tokenBlogId.equals(blogId)) {
                                return Response.custom(403, "Token不匹配，请重新验证密码！");
                            }
                        }
                    } catch (Exception e) {
                        return Response.custom(403, "Token已失效，请重新验证密码！");
                    }
                } else {
                    return Response.custom(403, "此文章受密码保护，请验证密码！");
                }
                break;
            default:
                break;
        }
        //查询该页面所有评论的数量
        Integer allComment = commentService.countByPageAndIsPublished(page, blogId, null);
        //查询该页面公开评论的数量
        Integer openComment = commentService.countByPageAndIsPublished(page, blogId, true);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<PageComment> pageInfo = new PageInfo<>(commentService.getPageCommentList(page, blogId, -1L));
        PageResult<PageComment> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        Map<String, Object> map = new HashMap<>(8);
        map.put("allComment", allComment);
        map.put("closeComment", allComment - openComment);
        map.put("comments", pageResult);
        return Response.ok("获取成功", map);
    }

    /**
     * 提交评论 又长又臭 能用就不改了:)
     * 单个ip，30秒内允许提交1次评论
     *
     * @param comment 评论DTO
     * @param request 获取ip
     * @param jwt     博主身份Token
     * @return
     */
    @AccessLimit(seconds = 30, maxCount = 1, msg = "30秒内只能提交一次评论")
    @PostMapping("/view/comment")
    public Response postComment(@RequestBody Comment comment,
                                HttpServletRequest request,
                                @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt) {
        //评论内容合法性校验
        if (StringUtils.isBlank(comment.getContent()) || comment.getContent().length() > 250 ||
                comment.getPage() == null || comment.getParentCommentId() == null) {
            return Response.failure("参数有误");
        }
        //是否访客的评论
        boolean isVisitorComment = false;
        //父评论
        CommentDO parentComment = null;
        //对于有指定父评论的评论，应该以父评论为准，只判断页面可能会被绕过“评论开启状态检测”
        if (comment.getParentCommentId() != -1) {
            //当前评论为子评论
            parentComment = commentService.getCommentById(comment.getParentCommentId());
            Integer page = parentComment.getPage();
            Long blogId = page == 0 ? parentComment.getBlog().getId() : null;
            comment.setPage(page);
            comment.setBlogId(blogId);
        } else {
            //当前评论为顶级评论
            if (comment.getPage() != 0) {
                comment.setBlogId(null);
            }
        }
        //判断是否可评论
        CommentOpenStateEnum openState = commentUtils.judgeCommentState(comment.getPage(), comment.getBlogId());
        switch (openState) {
            case NOT_FOUND:
                return Response.create(404, "该博客不存在");
            case CLOSE:
                return Response.create(403, "评论已关闭");
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
                        isVisitorComment = true;
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
                        isVisitorComment = true;
                    }
                } else {
                    //访客评论
                    //对访客的评论昵称、邮箱合法性校验
                    if (StringUtils.isBlank(comment.getNickname()) || StringUtils.isBlank(comment.getEmail()) || comment.getNickname().length() > 15) {
                        return Response.failure("参数有误");
                    }
                    commentUtils.setVisitorComment(comment, request);
                    isVisitorComment = true;
                }
                break;
            default:
                break;
        }
        commentService.saveComment(comment);
        commentUtils.judgeSendNotify(comment, isVisitorComment, parentComment);
        return Response.ok("评论成功");
    }
}