package com.yond.blog.web.blog.view.req;

import com.yond.common.req.PageReq;

import java.io.Serial;

/**
 * @Description:
 * @Author: WangJieLong
 */
public class BlogPageReq extends PageReq {
    
    @Serial
    private static final long serialVersionUID = 7923438536349685877L;
    
    private Integer categoryId;
    private Integer tagId;
    
    public Integer getCategoryId() {
        return categoryId;
    }
    
    public BlogPageReq setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }
    
    public Integer getTagId() {
        return tagId;
    }
    
    public BlogPageReq setTagId(Integer tagId) {
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
