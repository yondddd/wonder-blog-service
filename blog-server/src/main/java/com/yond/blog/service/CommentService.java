package com.yond.blog.service;

import com.yond.blog.entity.CommentDO;
import com.yond.blog.web.blog.admin.dto.CommentDTO;
import com.yond.blog.web.blog.view.vo.PageComment;
import com.yond.common.enums.CommentPageEnum;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface CommentService {
    
    Pair<Integer, List<CommentDTO>> pageBy(CommentPageEnum page, Long blogId, Integer pageNo, Integer pageSize);
    
    List<CommentDTO> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);
    
    List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId);
    
    CommentDTO getCommentById(Long id);
    
    void updateCommentPublishedById(Long commentId, Boolean published);
    
    void updateCommentNoticeById(Long commentId, Boolean notice);
    
    void deleteCommentById(Long commentId);
    
    void updateComment(CommentDO comment);
    
    int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);
    
    void saveComment(com.yond.blog.web.blog.view.dto.Comment comment);
    
    int countComment();
    
}
