package com.wonder.blog.web.admin.req;

import com.wonder.common.req.PageReq;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 */
public class BlogListPageReq extends PageReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -4356438509662111734L;

    private String title;
    private Long categoryId;
    private Long tagId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public BlogListPageReq setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Long getTagId() {
        return tagId;
    }

    public BlogListPageReq setTagId(Long tagId) {
        this.tagId = tagId;
        return this;
    }

    @Override
    public String toString() {
        return "BlogListPageReq{" +
                "title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", tagId=" + tagId +
                '}';
    }
}
