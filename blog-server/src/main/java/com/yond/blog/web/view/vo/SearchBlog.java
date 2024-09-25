package com.yond.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 关键字搜索博客
 * @Author: Yond
 */
public class SearchBlog implements Serializable {
    @Serial
    private static final long serialVersionUID = -154517852364468455L;
    private Long id;
    private String title;
    private String content;
    
    public SearchBlog() {
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String toString() {
        return "SearchBlog(id=" + this.getId() + ", title=" + this.getTitle() + ", content=" + this.getContent() + ")";
    }
}
