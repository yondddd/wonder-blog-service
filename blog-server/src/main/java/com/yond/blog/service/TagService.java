package com.yond.blog.service;

import com.yond.blog.entity.TagDO;
import com.yond.blog.web.blog.view.vo.TagBlogCount;

import java.util.List;

public interface TagService {
    List<TagDO> getTagList();

    List<TagDO> getTagListNotId();

    List<TagDO> getTagListByBlogId(Long blogId);

    void saveTag(TagDO tag);

    TagDO getTagById(Long id);

    TagDO getTagByName(String name);

    void deleteTagById(Long id);

    void updateTag(TagDO tag);

    List<TagBlogCount> getTagBlogCount();


}
