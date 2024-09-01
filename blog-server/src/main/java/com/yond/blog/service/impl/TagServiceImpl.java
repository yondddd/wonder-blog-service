package com.yond.blog.service.impl;

import com.yond.blog.cache.local.TagCache;
import com.yond.blog.entity.TagDO;
import com.yond.blog.mapper.TagMapper;
import com.yond.blog.service.TagService;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

/**
 * @Description: 博客标签业务层实现
 * @Author: Naccl
 * @Date: 2020-07-30
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<TagDO> listAll() {
        List<TagDO> cache = TagCache.get();
        if (cache == null) {
            cache = tagMapper.listAll();
            TagCache.set(cache);
        }
        return cache;
    }

    @Override
    public List<TagDO> listByIds(List<Long> ids) {
        return this.listAll().stream()
                .filter(x -> Set.copyOf(ids).contains(x.getId())).toList();
    }

    @Override
    public Pair<Integer, List<TagDO>> page(Integer pageNo, Integer pageSize) {
        List<TagDO> all = this.listAll();
        return Pair.of(all.size(), PageUtil.pageList(all, pageNo, pageSize));
    }

    @Override
    public TagDO getByName(String name) {
        return this.listAll().stream()
                .filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        if (tagMapper.deleteById(id) != 1) {
            throw new PersistenceException("标签删除失败");
        }
        flushCache();
    }


    @Override
    public Long insertSelective(TagDO tag) {
        tagMapper.insertSelective(tag);
        flushCache();
        return tag.getId();
    }

    @Override
    public Long saveIfAbsent(TagDO tag) {
        Assert.hasText(tag.getName(), "标签名字不应为空");
        TagDO exist = this.getByName(tag.getName());
        if (exist != null) {
            return exist.getId();
        }
        return this.insertSelective(tag);
    }

    @Override
    public void updateSelective(TagDO tag) {
        tagMapper.updateSelective(tag);
        flushCache();
    }

    private void flushCache() {
        TagCache.del();
    }

}
