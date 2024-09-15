package com.yond.blog.web.blog.admin.req;

import com.yond.common.req.PageReq;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 * @date 8/20/2024
 * @description
 */
public class BlogListPageReq extends PageReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -4356438509662111734L;

    private String title;
    private Integer categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "BlogListPageReq{" +
                "title='" + title + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
