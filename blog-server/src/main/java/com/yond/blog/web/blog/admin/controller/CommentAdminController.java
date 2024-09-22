package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.CommentDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CommentService;
import com.yond.blog.web.blog.admin.convert.CommentConverter;
import com.yond.blog.web.blog.admin.dto.CommentDTO;
import com.yond.blog.web.blog.admin.req.*;
import com.yond.blog.web.blog.admin.vo.CommentVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.enums.CommentPageEnum;
import com.yond.common.enums.EnableStatusEnum;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: 博客评论后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;

    @PostMapping("/page")
    public PageResponse<List<CommentVO>> page(@RequestBody CommentPageReq req) {
        Pair<Integer, List<CommentDTO>> pair = commentService.pageBy(CommentPageEnum.getByValue(req.getPage()), req.getBlogId(), req.getPageNo(), req.getPageSize());
        List<Long> blogIds = pair.getRight().stream().map(CommentDTO::getBlogId).toList();
        Map<Long, BlogDO> blogMap = blogService.listByIds(blogIds).stream().collect(Collectors.toMap(BlogDO::getId, Function.identity()));
        return PageResponse.<List<CommentVO>>custom().setTotal(pair.getLeft()).setData(CommentConverter.dto2vo(pair.getRight(), blogMap)).setSuccess();
    }

    @OperationLogger("更新评论公开状态")
    @PostMapping("/published")
    public Response<Boolean> published(@RequestBody CommentPublishedReq req) {
        CommentDO update = CommentDO.custom()
                .setId(req.getId())
                .setPublished(req.getPublished());
        commentService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("更新评论邮件提醒状态")
    @PostMapping("/notice")
    public Response<Boolean> notice(@RequestBody CommentNoticeReq req) {
        CommentDO update = CommentDO.custom()
                .setId(req.getId())
                .setNotice(req.getNotice());
        commentService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("删除评论")
    @PostMapping("/del")
    public Response<Boolean> del(@RequestBody CommentDelReq req) {
        CommentDO update = CommentDO.custom()
                .setId(req.getId())
                .setStatus(EnableStatusEnum.DELETE.getVal());
        commentService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("修改评论")
    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody CommentUpdateReq req) {
        CommentDO update = CommentDO.custom()
                .setId(req.getId())
                .setNickname(req.getNickname())
                .setAvatar(req.getAvatar())
                .setEmail(req.getEmail())
                .setWebsite(req.getWebsite())
                .setIp(req.getIp())
                .setContent(req.getContent());
        commentService.updateSelective(update);
        return Response.success();
    }

}
