package com.yond.blog.service.impl;

import com.yond.blog.cache.local.BlogCache;
import com.yond.blog.cache.local.TagCache;
import com.yond.blog.entity.TagDO;
import com.yond.blog.mapper.TagMapper;
import com.yond.blog.service.TagService;
import com.yond.blog.web.blog.view.vo.TagBlogCount;
import com.yond.common.exception.NotFoundException;
import com.yond.common.exception.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<TagDO> data = tagMapper.listAll();
        return List.of();
    }

    @Override
    public List<TagDO> getTagList() {
        return tagMapper.getTagList();
    }

    @Override
    public List<TagDO> getTagListNotId() {
        List<TagDO> tagListFromRedis = TagCache.get();
        if (tagListFromRedis != null) {
            return tagListFromRedis;
        }
        List<TagDO> tagList = tagMapper.getTagListNotId();
        TagCache.set(tagList);
        return tagList;
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
    public TagDO getTagById(Long id) {
        TagDO tag = tagMapper.getTagById(id);
        if (tag == null) {
            throw new NotFoundException("标签不存在");
        }
        return tag;
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

}
