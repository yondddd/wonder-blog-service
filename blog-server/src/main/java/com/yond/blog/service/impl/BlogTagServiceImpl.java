package com.yond.blog.service.impl;

import com.yond.blog.cache.local.BlogTagCache;
import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.mapper.BlogTagMapper;
import com.yond.blog.service.BlogTagService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.blog.view.vo.TagBlogCount;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yond
 * @date 8/23/2024
 * @description
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
    public List<TagBlogCount> getTagBlogCount() {
        Map<Long, List<BlogTagDO>> map = this.listAll().stream().collect(Collectors.groupingBy(BlogTagDO::getTagId));
        Map<Long, TagDO> tagMap = tagService.listAll().stream().collect(Collectors.toMap(TagDO::getId, Function.identity()));
        List<TagBlogCount> data = new ArrayList<>();
        for (Map.Entry<Long, List<BlogTagDO>> entry : map.entrySet()) {
            TagBlogCount count = new TagBlogCount();
            count.setId(entry.getKey());
            TagDO tag = tagMap.get(entry.getKey());
            if (tag != null) {
                count.setName(tag.getName());
            }
            count.setValue(entry.getValue().size());
            data.add(count);
        }
        return data;
    }
}
