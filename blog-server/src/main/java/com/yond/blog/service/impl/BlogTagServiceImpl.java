package com.yond.blog.service.impl;

import com.yond.blog.cache.local.BlogTagCache;
import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.mapper.BlogTagMapper;
import com.yond.blog.service.BlogTagService;
import com.yond.blog.service.TagService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yond
 * @date 8/23/2024
 * @description
 */
@Service
public class BlogTagServiceImpl implements BlogTagService {

    private final BlogTagMapper blogTagMapper;
    private final TagService tagService;

    public BlogTagServiceImpl(BlogTagMapper blogTagMapper, TagService tagService) {
        this.blogTagMapper = blogTagMapper;
        this.tagService = tagService;
    }
    
    @Override
    public List<BlogTagDO> listAll() {
        List<BlogTagDO> cache = BlogTagCache.listAll();
        if (cache==null){
            cache=blogTagMapper.listAll();
            BlogTagCache.setAll(cache);
        }
        return cache;
    }
    
    @Override
    public List<BlogTagDO> listByBlogId(Long blogId) {
        return blogTagMapper.listByBlogId(blogId);
    }

    @Override
    public List<TagDO> listTagsByBlogId(Long blogId) {
        List<BlogTagDO> blogTags = this.listByBlogId(blogId);
        return tagService.listAll();
    }
    
    @Override
    public void saveBlogTag(Long blogId, List<Long> tagIds) {
        List<BlogTagDO> exist = this.listByBlogId(blogId);
        List<BlogTagDO> save=new ArrayList<>();
        Set<Long> set = new HashSet<>(tagIds);
        for (BlogTagDO blogTag : exist) {
            boolean remove = set.remove(blogTag.getTagId());
            if (remove){
                continue;
            }
            BlogTagDO.custom()
                    .setBlogId()
                    .setTagId()
        }
        if (CollectionUtils.isNotEmpty(set)){
            this.deleteByIds(new ArrayList<>(set));
        }
    }
    
    @Override
    public void deleteByIds(List<Long> ids) {
        blogTagMapper.deleteByIds(ids);
    }
}
