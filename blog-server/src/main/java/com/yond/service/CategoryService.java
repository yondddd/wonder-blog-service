package com.yond.service;

import com.yond.entity.CategoryDO;

import java.util.List;

public interface CategoryService {

    List<CategoryDO> listAll();

    void save(CategoryDO category);

    CategoryDO getById(Long id);

    CategoryDO getByName(String name);

    void deleteById(Long id);

    void update(CategoryDO category);

}
