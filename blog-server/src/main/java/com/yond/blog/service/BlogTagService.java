package com.yond.blog.service;

import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.TagDO;

import java.util.List;

/**
 * @Author Yond
 * @date 8/23/2024
 * @description
 */
public interface BlogTagService {

    List<BlogTagDO> listAll();

    List<BlogTagDO> listByBlogId(Long blogId);

    List<TagDO> listTagsByBlogId(Long blogId);

    Long insertSelective(BlogTagDO record);

    void saveBlogTag(Long blogId, List<Long> tagIds);

    void deleteByIds(List<Long> ids);

}
