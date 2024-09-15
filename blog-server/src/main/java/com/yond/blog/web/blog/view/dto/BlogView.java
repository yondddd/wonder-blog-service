package com.yond.blog.web.blog.view.dto;

/**
 * @Description: 博客浏览量
 * @Author: Yond
 */
public class BlogView {
    private Long id;
    private Integer views;

    public BlogView() {
    }

    public Long getId() {
        return this.id;
    }

    public Integer getViews() {
        return this.views;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String toString() {
        return "BlogView(id=" + this.getId() + ", views=" + this.getViews() + ")";
    }
}
