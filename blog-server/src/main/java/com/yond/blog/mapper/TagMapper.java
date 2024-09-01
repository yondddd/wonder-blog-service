package com.yond.blog.mapper;

import com.yond.blog.entity.TagDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客标签持久层接口
 * @Author: Naccl
 * @Date: 2020-07-30
 */
@Mapper
@Repository
public interface TagMapper {

    List<TagDO> listAll();

    int insertSelective(TagDO tagDO);

    int updateSelective(TagDO tagDO);

    int deleteById(Long id);

}
