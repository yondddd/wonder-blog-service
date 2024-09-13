package com.yond.blog.service;

import com.yond.blog.entity.CommentDO;
import com.yond.blog.web.blog.admin.dto.CommentDTO;
import com.yond.common.enums.CommentOpenStateEnum;
import com.yond.common.enums.CommentPageEnum;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface CommentService {

    Pair<Integer, List<CommentDTO>> pageBy(CommentPageEnum page, Long blogId, Integer pageNo, Integer pageSize);

    void updateSelective(CommentDO comment);

    void insertSelective(CommentDO comment);

    int countComment();

    CommentOpenStateEnum getPageCommentStatus(Integer page, Long blogId);

    CommentDO getById(Long id);
}
