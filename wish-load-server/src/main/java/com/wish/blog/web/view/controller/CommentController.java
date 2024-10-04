package com.wish.blog.web.view.controller;

import com.wish.blog.entity.CommentDO;
import com.wish.blog.service.CommentService;
import com.wish.blog.util.ip.IpAddressUtils;
import com.wish.blog.util.jwt.JwtUtil;
import com.wish.blog.web.admin.dto.CommentDTO;
import com.wish.blog.web.view.convert.CommentConverter;
import com.wish.blog.web.view.req.CommentLeaveReq;
import com.wish.blog.web.view.req.CommentPageViewReq;
import com.wish.blog.web.view.vo.CommentViewVO;
import com.wish.common.annotation.AccessLimit;
import com.wish.common.constant.CommonConstant;
import com.wish.common.constant.JwtConstant;
import com.wish.common.enums.CommentOpenStateEnum;
import com.wish.common.enums.CommentPageEnum;
import com.wish.common.enums.EnableStatusEnum;
import com.wish.common.resp.PageResponse;
import com.wish.common.resp.Response;
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
 * @Author: Yond
 */
@RestController
@RequestMapping("/view/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/page")
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
        Pair<Integer, List<CommentDTO>> pair = commentService.viewPageBy(req.getPage(), req.getBlogId(), req.getPage(), req.getPageSize());
        return PageResponse.<List<CommentViewVO>>custom().setData(CommentConverter.dto2vo(pair.getRight())).setTotal(pair.getLeft()).setSuccess();
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
        Long parentId = req.getParentCommentId() == null ? CommonConstant.ROOT_ID : req.getParentCommentId();
        boolean isBlogComment = CommentPageEnum.BLOG.getId().equals(page);
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
        CommentDO reply = CommentDO.custom();
        reply.setPage(page);
        reply.setBlogId(blogId);
        reply.setParentId(parentId);
        reply.setNickname(req.getNickname());
        reply.setEmail(req.getEmail());
        reply.setContent(req.getContent());
        reply.setAvatar(req.getAvatar());
        reply.setWebsite(req.getWebsite());
        reply.setIp(IpAddressUtils.getIpAddress(request));
        reply.setPublished(true);
        reply.setAdminComment(isAdmin(jwt));
        reply.setNotice(req.getNotice());
        reply.setQq(req.getQq());
        reply.setStatus(EnableStatusEnum.ENABLE.getVal());
        reply.setCreateTime(new Date());
        commentService.insertSelective(reply);
        return Response.success();
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

    private boolean isAdmin(String jwt) {
        boolean tokenIsExist = JwtUtil.judgeTokenIsExist(jwt);
        if (!tokenIsExist) {
            return false;
        }
        try {
            Claims claims = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET);
            String subject = claims.getSubject();
            if (subject.startsWith(JwtConstant.ADMIN_PREFIX)) {
                return claims.getExpiration().after(new Date());
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}