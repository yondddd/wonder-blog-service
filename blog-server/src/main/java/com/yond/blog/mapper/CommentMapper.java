package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Param;

import com.yond.blog.entity.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客评论持久层接口
 * @Author: Yond
 * @Date: 2020-08-03
 */
@Mapper
@Repository
public interface CommentMapper {

    List<CommentDO> listAll();

    int insertSelective(CommentDO commentDO);

    int updateSelective(CommentDO commentDO);
}
