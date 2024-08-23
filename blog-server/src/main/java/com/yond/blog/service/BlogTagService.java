package com.yond.blog.service;

import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.TagDO;

import java.util.List;

/**
 * @author yond
 * @date 8/23/2024
 * @description
 */
public interface BlogTagService {

    List<BlogTagDO> listByBlogId(Long blogId);

    List<TagDO> listTagsByBlogId(Long blogId);
}
