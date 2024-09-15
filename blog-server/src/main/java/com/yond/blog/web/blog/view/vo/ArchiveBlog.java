package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 归档页面博客简要信息
 * @Author: Yond
 */
public class ArchiveBlog implements Serializable {
    @Serial
    private static final long serialVersionUID = -846138106510742666L;
    private Long id;
    private String title;
    private String day;
    private String password;
    private Boolean privacy;

    public ArchiveBlog() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDay() {
        return this.day;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean getPrivacy() {
        return this.privacy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public String toString() {
        return "ArchiveBlog(id=" + this.getId() + ", title=" + this.getTitle() + ", day=" + this.getDay() + ", password=" + this.getPassword() + ", privacy=" + this.getPrivacy() + ")";
    }
}
