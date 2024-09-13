package com.yond.blog.service;

import com.yond.blog.entity.CommentDO;
import com.yond.blog.web.blog.admin.dto.CommentDTO;
import com.yond.blog.web.blog.view.vo.CommentViewVO;
import com.yond.common.enums.CommentOpenStateEnum;
import com.yond.common.enums.CommentPageEnum;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface CommentService {
    
    Pair<Integer, List<CommentDTO>> pageBy(CommentPageEnum page, Long blogId, Integer pageNo, Integer pageSize);
    
    List<CommentViewVO> getPageCommentList(Integer page, Long blogId, Long parentCommentId);
    
    CommentDTO getCommentById(Long id);
    
    void updateCommentPublishedById(Long commentId, Boolean published);
    
    void deleteCommentById(Long commentId);
    
    void updateSelective(CommentDO comment);
    
    void saveComment(com.yond.blog.web.blog.view.dto.Comment comment);
    
    int countComment();
    
    CommentOpenStateEnum getPageCommentStatus(Integer page, Long blogId);
    
    CommentDO getById(Long id);
}
