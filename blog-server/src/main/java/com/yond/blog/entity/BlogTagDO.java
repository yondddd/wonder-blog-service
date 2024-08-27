package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName blog_tag
 */
public class BlogTagDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5350459604914013492L;

    private Long id;
    private Long blogId;
    private Long tagId;
    private Date createTime;


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

    public Date getCreateTime() {
        return createTime;
    }

    public BlogTagDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "BlogTagDO{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", tagId=" + tagId +
                ", createTime=" + createTime +
                '}';
    }
}