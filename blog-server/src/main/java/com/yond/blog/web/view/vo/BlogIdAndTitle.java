package com.yond.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 评论管理页面按博客title查询评论
 * @Author: Yond
 */
public class BlogIdAndTitle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1649191154613069726L;
    private Long id;
    private String title;
    
    public BlogIdAndTitle() {
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String toString() {
        return "BlogIdAndTitle(id=" + this.getId() + ", title=" + this.getTitle() + ")";
    }
}
