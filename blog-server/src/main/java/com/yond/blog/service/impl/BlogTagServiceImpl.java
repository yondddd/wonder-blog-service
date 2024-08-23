package com.yond.blog.service.impl;

import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.mapper.BlogTagMapper;
import com.yond.blog.service.BlogTagService;
import com.yond.blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<BlogTagDO> listByBlogId(Long blogId) {
        return blogTagMapper.listByBlogId(blogId);
    }

    @Override
    public List<TagDO> listTagsByBlogId(Long blogId) {
        List<BlogTagDO> blogTags = this.listByBlogId(blogId);
        return tagService.listAll();
    }
}
