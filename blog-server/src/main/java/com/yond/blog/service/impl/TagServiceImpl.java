package com.yond.blog.service.impl;

import com.yond.blog.cache.local.BlogCache;
import com.yond.blog.cache.local.TagCache;
import com.yond.blog.entity.TagDO;
import com.yond.blog.mapper.TagMapper;
import com.yond.blog.service.TagService;
import com.yond.blog.web.blog.view.vo.TagBlogCount;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
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

    private final TagMapper tagMapper;

    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

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
    public List<TagDO> getTagListByBlogId(Long blogId) {
        return tagMapper.getTagListByBlogId(blogId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTag(TagDO tag) {
        if (tagMapper.saveTag(tag) != 1) {
            throw new PersistenceException("标签添加失败");
        }
        TagCache.del();
    }

    @Override
    public TagDO getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTagById(Long id) {
        if (tagMapper.deleteTagById(id) != 1) {
            throw new PersistenceException("标签删除失败");
        }
        TagCache.del();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTag(TagDO tag) {
        if (tagMapper.updateTag(tag) != 1) {
            throw new PersistenceException("标签更新失败");
        }
        TagCache.del();
        //修改了标签名或颜色，可能有首页文章关联了标签，也要更新首页缓存
        BlogCache.delInfo();
    }

    @Override
    public List<TagBlogCount> getTagBlogCount() {
        return tagMapper.getTagBlogCount();
    }
    
    @Override
    public Long insertSelective(TagDO tag) {
        tagMapper.insertSelective(tag);
        TagCache.del();
        return tag.getId();
    }
    
    @Override
    public Long saveIfAbsent(TagDO tag) {
        Assert.hasText(tag.getName(),"标签名字不应为空");
        TagDO exist = this.getTagByName(tag.getName());
        if (exist!=null){
            return exist.getId();
        }
        return this.insertSelective(tag);
    }
}
