package com.yond.mapper;

import com.yond.entity.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客分类持久层接口
 * @Author: Naccl
 * @Date: 2020-07-29
 */
@Mapper
@Repository
public interface CategoryMapper {

    List<CategoryDO> listAll();

    int save(CategoryDO category);

    CategoryDO getById(Long id);

    CategoryDO getByName(String name);

    int deleteById(Long id);

    int update(CategoryDO category);

}
