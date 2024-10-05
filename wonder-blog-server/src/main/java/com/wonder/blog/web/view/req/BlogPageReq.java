package com.wonder.blog.web.view.req;

import com.wonder.common.req.PageReq;

import java.io.Serial;

/**
 * @Description:
 * @Author: Yond
 */
public class BlogPageReq extends PageReq {

    @Serial
    private static final long serialVersionUID = 7923438536349685877L;

    private Long categoryId;
    private Long tagId;

    public Long getCategoryId() {
        return categoryId;
    }

    public BlogPageReq setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Long getTagId() {
        return tagId;
    }

    public BlogPageReq setTagId(Long tagId) {
        this.tagId = tagId;
        return this;
    }

    @Override
    public String toString() {
        return "BlogPageReq{" +
                "categoryId=" + categoryId +
                ", tagId=" + tagId +
                '}';
    }
}
