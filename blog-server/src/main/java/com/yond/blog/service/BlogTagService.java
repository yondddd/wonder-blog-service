package com.yond.blog.service;

import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.TagDO;

import java.util.List;
import java.util.Map;

/**
 * @Author Yond
 */
public interface BlogTagService {
    
    List<BlogTagDO> listAll();
    
    List<BlogTagDO> listByBlogId(Long blogId);
    
    List<TagDO> listTagsByBlogId(Long blogId);
    
    Map<Long, List<TagDO>> listTagsByBlogIds(List<Long> blogIds);
    
    Long insertSelective(BlogTagDO record);
    
    void saveBlogTag(Long blogId, List<Long> tagIds);
    
    void deleteByIds(List<Long> ids);
    
}
