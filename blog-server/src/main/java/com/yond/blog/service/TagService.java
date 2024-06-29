package com.yond.blog.service;

import com.yond.blog.entity.Tag;
import com.yond.blog.model.vo.TagBlogCount;

import java.util.List;

public interface TagService {
    List<Tag> getTagList();

    List<Tag> getTagListNotId();

    List<Tag> getTagListByBlogId(Long blogId);

    void saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    void deleteTagById(Long id);

    void updateTag(Tag tag);

    List<TagBlogCount> getTagBlogCount();


}
