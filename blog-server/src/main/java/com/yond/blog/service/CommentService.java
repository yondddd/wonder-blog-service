package com.yond.blog.service;

import com.yond.blog.entity.CommentDO;
import com.yond.blog.web.blog.view.vo.PageComment;

import java.util.List;

public interface CommentService {
    List<CommentDO> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId);

    CommentDO getCommentById(Long id);

    void updateCommentPublishedById(Long commentId, Boolean published);

    void updateCommentNoticeById(Long commentId, Boolean notice);

    void deleteCommentById(Long commentId);

    void deleteCommentsByBlogId(Long blogId);

    void updateComment(CommentDO comment);

    int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

    void saveComment(com.yond.blog.web.blog.view.dto.Comment comment);

    int countComment();
}
