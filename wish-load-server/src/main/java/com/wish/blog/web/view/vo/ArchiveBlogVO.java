package com.wish.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 归档页面博客简要信息
 * @Author: Yond
 */
public class ArchiveBlogVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -846138106510742666L;
    
    private Long id;
    private String title;
    private String day;
    private Boolean privacy;
    
    public ArchiveBlogVO() {
    }
    
    
    public Long getId() {
        return id;
    }
    
    public ArchiveBlogVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public ArchiveBlogVO setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getDay() {
        return day;
    }
    
    public ArchiveBlogVO setDay(String day) {
        this.day = day;
        return this;
    }
    
    public Boolean getPrivacy() {
        return privacy;
    }
    
    public ArchiveBlogVO setPrivacy(Boolean privacy) {
        this.privacy = privacy;
        return this;
    }
    
    @Override
    public String toString() {
        return "ArchiveBlogVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", day='" + day + '\'' +
                ", privacy=" + privacy +
                '}';
    }
}
