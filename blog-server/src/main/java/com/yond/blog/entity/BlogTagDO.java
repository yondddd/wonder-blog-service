package com.yond.blog.entity;

import java.io.Serializable;

/**
 * 
 * @TableName blog_tag
 */
public class BlogTagDO implements Serializable {

    private Long id;
    private Long blogId;
    private Long tagId;

    private static final long serialVersionUID = 1L;
    
    public static BlogTagDO custom(){
        return new BlogTagDO();
    }
    public Long getId() {
        return id;
    }
    
    public BlogTagDO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Long getBlogId() {
        return blogId;
    }
    
    public BlogTagDO setBlogId(Long blogId) {
        this.blogId = blogId;
        return this;
    }
    
    public Long getTagId() {
        return tagId;
    }
    
    public BlogTagDO setTagId(Long tagId) {
        this.tagId = tagId;
        return this;
    }
    
    @Override
    public String toString() {
        return "BlogTagDO{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}