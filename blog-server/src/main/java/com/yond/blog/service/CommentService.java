package com.yond.blog.service;

import com.yond.blog.entity.Comment;
import com.yond.blog.model.vo.PageComment;

import java.util.List;

public interface CommentService {
    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId);

    Comment getCommentById(Long id);

    void updateCommentPublishedById(Long commentId, Boolean published);

    void updateCommentNoticeById(Long commentId, Boolean notice);

    void deleteCommentById(Long commentId);

    void deleteCommentsByBlogId(Long blogId);

    void updateComment(Comment comment);

    int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

    void saveComment(com.yond.blog.model.dto.Comment comment);

    int countComment();
}
