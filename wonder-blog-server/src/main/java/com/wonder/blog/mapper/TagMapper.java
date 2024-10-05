package com.wonder.blog.mapper;

import com.wonder.blog.entity.TagDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客标签持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface TagMapper {

    List<TagDO> listAll();

    int insertSelective(TagDO tagDO);

    int updateSelective(TagDO tagDO);

    int deleteById(Long id);

}
