package com.wonder.blog.mapper;

import com.wonder.blog.entity.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客评论持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface CommentMapper {

    List<CommentDO> listAll();

    int insertSelective(CommentDO commentDO);

    int updateSelective(CommentDO commentDO);
}
