package com.yond.service.impl;

import com.yond.cache.BlogInfoCache;
import com.yond.cache.CategoryCache;
import com.yond.common.exception.PersistenceException;
import com.yond.entity.CategoryDO;
import com.yond.mapper.CategoryMapper;
import com.yond.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 博客分类业务层实现
 * @Author: Naccl
 * @Date: 2020-07-29
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final BlogInfoCache blogInfoCache;
    private final CategoryCache categoryCache;

    public CategoryServiceImpl(CategoryMapper categoryMapper, BlogInfoCache blogInfoCache, CategoryCache categoryCache) {
        this.categoryMapper = categoryMapper;
        this.blogInfoCache = blogInfoCache;
        this.categoryCache = categoryCache;
    }

    @Override
    public List<CategoryDO> listAll() {
        List<CategoryDO> data = categoryCache.get();
        if (data != null) {
            return data;
        }
        data = categoryMapper.listAll();
        categoryCache.set(data);
        return data;
    }


    @Override
    public void save(CategoryDO category) {
        if (categoryMapper.save(category) != 1) {
            throw new PersistenceException("分类添加失败");
        }
        categoryCache.del();
    }

    @Override
    public CategoryDO getById(Long id) {
        return this.listAll().stream()
                .filter(x -> id.equals(x.getId())).findFirst().orElse(null);
    }

    @Override
    public CategoryDO getByName(String name) {
        return this.listAll().stream()
                .filter(x -> name.equals(x.getName())).findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        if (categoryMapper.deleteById(id) != 1) {
            throw new PersistenceException("删除分类失败");
        }
        categoryCache.del();
    }

    @Override
    public void update(CategoryDO category) {
        if (categoryMapper.update(category) != 1) {
            throw new PersistenceException("分类更新失败");
        }
        categoryCache.del();
        // 修改了分类名，可能有首页文章关联了分类，也要更新首页缓存
        blogInfoCache.delete();
    }

}
