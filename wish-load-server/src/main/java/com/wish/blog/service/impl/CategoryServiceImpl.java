package com.wish.blog.service.impl;

import com.wish.blog.cache.local.CategoryCache;
import com.wish.blog.entity.CategoryDO;
import com.wish.blog.mapper.CategoryMapper;
import com.wish.blog.service.CategoryService;
import com.wish.common.exception.PersistenceException;
import com.wish.common.utils.page.PageUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;

/**
 * @Description: 博客分类业务层实现
 * @Author: Yond
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryMapper categoryMapper;
    
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    
    @Override
    public Pair<Integer, List<CategoryDO>> page(Integer pageNo, Integer pageSize) {
        List<CategoryDO> all = this.listAll();
        return Pair.of(all.size(), PageUtil.pageList(all, pageNo, pageSize));
    }
    
    @Override
    public List<CategoryDO> listAll() {
        List<CategoryDO> data = CategoryCache.get();
        if (data != null) {
            return data;
        }
        data = categoryMapper.listAll();
        CategoryCache.set(data);
        return data;
    }
    
    
    @Override
    public CategoryDO getById(Long id) {
        return this.listAll().stream()
                .filter(x -> id.equals(x.getId())).findFirst().orElse(null);
    }
    
    @Override
    public List<CategoryDO> listByIds(List<Long> ids) {
        return this.listAll().stream()
                .filter(x -> new HashSet<>(ids).contains(x.getId())).toList();
    }
    
    @Override
    public CategoryDO getByName(String name) {
        return this.listAll().stream()
                .filter(x -> name.equals(x.getName())).findFirst().orElse(null);
    }
    
    @Override
    public Long insertSelective(CategoryDO category) {
        categoryMapper.insertSelective(category);
        CategoryCache.del();
        return category.getId();
    }
    
    @Override
    public void updateSelective(CategoryDO category) {
        if (categoryMapper.updateSelective(category) != 1) {
            throw new PersistenceException("分类更新失败");
        }
        CategoryCache.del();
    }
    
    @Override
    public void deleteById(Long id) {
        if (categoryMapper.deleteById(id) != 1) {
            throw new PersistenceException("删除分类失败");
        }
        CategoryCache.del();
    }
    
    @Override
    public Long saveIfAbsent(String name) {
        Assert.hasText(name, "分类名称为空");
        CategoryDO exist = this.getByName(name);
        if (exist != null) {
            return exist.getId();
        }
        return this.insertSelective(CategoryDO.custom().setName(name));
    }
    
}
