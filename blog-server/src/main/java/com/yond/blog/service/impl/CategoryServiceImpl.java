package com.yond.blog.service.impl;

import com.yond.blog.cache.local.BlogCache;
import com.yond.blog.cache.local.CategoryCache;
import com.yond.blog.entity.CategoryDO;
import com.yond.blog.mapper.CategoryMapper;
import com.yond.blog.service.CategoryService;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @Description: 博客分类业务层实现
 * @Author: Naccl
 * @Date: 2020-07-29
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
    public void save(CategoryDO category) {
        if (categoryMapper.save(category) != 1) {
            throw new PersistenceException("分类添加失败");
        }
        CategoryCache.del();
    }

    @Override
    public void update(CategoryDO category) {
        if (categoryMapper.update(category) != 1) {
            throw new PersistenceException("分类更新失败");
        }
        CategoryCache.del();
        // 修改了分类名，可能有首页文章关联了分类，也要更新首页缓存
        BlogCache.delInfo();
    }

    @Override
    public void deleteById(Long id) {
        if (categoryMapper.deleteById(id) != 1) {
            throw new PersistenceException("删除分类失败");
        }
        CategoryCache.del();
    }


}
