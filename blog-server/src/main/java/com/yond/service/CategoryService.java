package com.yond.service;

import com.yond.entity.CategoryDO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface CategoryService {

    Pair<Integer, List<CategoryDO>> page(Integer pageNo, Integer pageSize);

    List<CategoryDO> listAll();

    void save(CategoryDO category);

    CategoryDO getById(Long id);

    CategoryDO getByName(String name);

    void deleteById(Long id);

    void update(CategoryDO category);

}
