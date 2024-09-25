package com.yond.blog.service;

import com.yond.blog.entity.CommentDO;
import com.yond.blog.web.admin.dto.CommentDTO;
import com.yond.blog.web.view.vo.CommentViewVO;
import com.yond.common.enums.CommentOpenStateEnum;
import com.yond.common.enums.CommentPageEnum;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface CommentService {
    
    List<CommentDO> listAll();
    
    Pair<Integer, List<CommentDTO>> pageBy(CommentPageEnum page, Long blogId, Integer pageNo, Integer pageSize);
    
    CommentDO getById(Long id);
    
    void updateSelective(CommentDO comment);
    
    void insertSelective(CommentDO comment);
    
    CommentOpenStateEnum getPageCommentStatus(Integer page, Long blogId);
    
    Pair<Integer, List<CommentViewVO>> viewPageBy(Integer page, Long blogId, Integer pageNo, Integer pageSize);
}
