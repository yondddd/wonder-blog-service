package com.yond.blog.service;

import com.yond.blog.entity.CategoryDO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface CategoryService {

    Pair<Integer, List<CategoryDO>> page(Integer pageNo, Integer pageSize);

    List<CategoryDO> listAll();

    CategoryDO getById(Long id);

    List<CategoryDO> listByIds(List<Long> ids);

    CategoryDO getByName(String name);

    Long insertSelective(CategoryDO category);

    void updateSelective(CategoryDO category);

    void deleteById(Long id);

    Long saveIfAbsent(String name);
}
