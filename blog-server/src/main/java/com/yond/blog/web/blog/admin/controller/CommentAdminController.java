package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.CommentDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CommentService;
import com.yond.blog.web.blog.admin.req.CommentPageReq;
import com.yond.blog.web.blog.admin.vo.CommentVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.enums.CommentPageEnum;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 博客评论后台管理
 * @Author: Naccl
 * @Date: 2020-08-03
 */
@RestController
@RequestMapping("/admin/comment")
public class CommentAdminController {
    
    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;
    
    @GetMapping("/page")
    public PageResponse<List<CommentVO>> page(@RequestBody CommentPageReq req) {
        Pair<Integer, List<CommentDO>> pair = commentService.pageBy(CommentPageEnum.getByValue(req.getPage()), req.getBlogId(), req.getPageNo(), req.getPageSize());
        PageInfo<CommentDO> pageInfo = new PageInfo<>(comments);
        return Response.success(pageInfo);
    }
    
    /**
     * 获取所有博客id和title 供评论分类的选择
     *
     * @return
     */
    @GetMapping("/blogIdAndTitle")
    public Response<List<BlogDO>> blogIdAndTitle() {
        List<BlogDO> blogs = blogService.getIdAndTitleList();
        return Response.success(blogs);
    }
    
    /**
     * 更新评论公开状态
     *
     * @param id        评论id
     * @param published 是否公开
     * @return
     */
    @OperationLogger("更新评论公开状态")
    @PutMapping("/comment/published")
    public Response<Boolean> updatePublished(@RequestParam Long id, @RequestParam Boolean published) {
        commentService.updateCommentPublishedById(id, published);
        return Response.success();
    }
    
    /**
     * 更新评论接收邮件提醒状态
     *
     * @param id     评论id
     * @param notice 是否接收提醒
     * @return
     */
    @OperationLogger("更新评论邮件提醒状态")
    @PutMapping("/comment/notice")
    public Response<Boolean> updateNotice(@RequestParam Long id, @RequestParam Boolean notice) {
        commentService.updateCommentNoticeById(id, notice);
        return Response.success();
    }
    
    /**
     * 按id删除该评论及其所有子评论
     *
     * @param id 评论id
     * @return
     */
    @OperationLogger("删除评论")
    @DeleteMapping("/comment")
    public Response<Boolean> delete(@RequestParam Long id) {
        commentService.deleteCommentById(id);
        return Response.success();
    }
    
    /**
     * 修改评论
     *
     * @param comment 评论实体
     * @return
     */
    @OperationLogger("修改评论")
    @PutMapping("/comment")
    public Response<Boolean> updateComment(@RequestBody CommentDO comment) {
        if (StringUtils.isBlank(comment.getNickname())
                || StringUtils.isBlank(comment.getAvatar()) || StringUtils.isBlank(comment.getEmail()) ||
                StringUtils.isBlank(comment.getIp()) || StringUtils.isBlank(comment.getContent())) {
            return Response.<Boolean>custom().setFailure("参数有误");
        }
        commentService.updateComment(comment);
        return Response.success();
    }
}
