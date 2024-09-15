package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 最新推荐博客
 * @Author: Yond
 */
public class NewBlog implements Serializable {
    @Serial
    private static final long serialVersionUID = -6188444658536941480L;
    private Long id;
    private String title;
    private String password;
    private Boolean privacy;

    public NewBlog() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public String toString() {
        return "NewBlog(id=" + this.getId() + ", title=" + this.getTitle() + ", password=" + this.getPassword() + ", privacy=" + this.getPrivacy() + ")";
    }
}
