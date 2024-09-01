package com.yond.blog.service;

import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.web.blog.view.vo.TagBlogCount;

import java.util.List;

/**
 * @author yond
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

    List<TagBlogCount> getTagBlogCount();
}
