package com.wish.blog.service.impl;

import com.wish.blog.cache.local.BlogTagCache;
import com.wish.blog.entity.BlogTagDO;
import com.wish.blog.entity.TagDO;
import com.wish.blog.mapper.BlogTagMapper;
import com.wish.blog.service.BlogTagService;
import com.wish.blog.service.TagService;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author Yond
 */
@Service
public class BlogTagServiceImpl implements BlogTagService {

    @Resource
    private BlogTagMapper blogTagMapper;
    @Resource
    private TagService tagService;

    @Override
    public List<BlogTagDO> listAll() {
        List<BlogTagDO> cache = BlogTagCache.listAll();
        if (cache == null) {
            cache = blogTagMapper.listAll();
            BlogTagCache.setAll(cache);
        }
        return cache;
    }

    @Override
    public List<BlogTagDO> listByBlogId(Long blogId) {
        return this.listAll().stream()
                .filter(x -> blogId.equals(x.getBlogId())).toList();
    }

    @Override
    public List<TagDO> listTagsByBlogId(Long blogId) {
        List<BlogTagDO> blogTags = this.listByBlogId(blogId);
        return tagService.listByIds(blogTags.stream().map(BlogTagDO::getTagId).toList());
    }

    @Override
    public Map<Long, List<TagDO>> listTagsByBlogIds(List<Long> blogIds) {
        List<BlogTagDO> list = this.listAll().stream()
                .filter(x -> blogIds.contains(x.getBlogId())).toList();

        List<Long> tagIds = list.stream().map(BlogTagDO::getTagId).toList();
        Map<Long, TagDO> tagMap = tagService.listByIds(tagIds).stream().collect(Collectors.toMap(TagDO::getId, Function.identity()));

        Map<Long, List<TagDO>> data = new HashMap<>();
        for (Map.Entry<Long, List<BlogTagDO>> entry : list.stream().collect(Collectors.groupingBy(BlogTagDO::getBlogId)).entrySet()) {
            List<TagDO> tag = new ArrayList<>();
            for (BlogTagDO blogTagDO : entry.getValue()) {
                TagDO tagDO = tagMap.get(blogTagDO.getTagId());
                if (tagDO != null) {
                    tag.add(tagDO);
                }
            }
            data.put(entry.getKey(), tag);
        }
        return data;
    }

    @Override
    public Long insertSelective(BlogTagDO record) {
        blogTagMapper.insertSelective(record);
        BlogTagCache.removeAll();
        return record.getId();
    }

    @Override
    public void saveBlogTag(Long blogId, List<Long> tagIds) {
        Set<Long> existTagIds = this.listByBlogId(blogId).stream()
                .map(BlogTagDO::getTagId).collect(Collectors.toSet());
        List<BlogTagDO> insert = new ArrayList<>();
        for (Long tagId : tagIds) {
            boolean remove = existTagIds.remove(tagId);
            if (remove) {
                continue;
            }
            BlogTagDO item = BlogTagDO.custom()
                    .setBlogId(blogId)
                    .setTagId(tagId);
            insert.add(item);
        }
        for (BlogTagDO item : insert) {
            this.insertSelective(item);
        }
        if (CollectionUtils.isNotEmpty(existTagIds)) {
            this.deleteByIds(new ArrayList<>(existTagIds));
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        blogTagMapper.deleteByIds(ids);
        BlogTagCache.removeAll();
    }

    @Override
    public List<BlogTagDO> listByTagId(Long tagId) {
        return this.listAll().stream()
                .filter(x -> tagId.equals(x.getTagId())).toList();
    }

}
