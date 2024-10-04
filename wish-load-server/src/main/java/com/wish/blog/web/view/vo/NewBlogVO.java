package com.wish.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 最新推荐博客
 * @Author: Yond
 */
public class NewBlogVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6188444658536941480L;

    private Long id;
    private String title;
    private Boolean privacy;

    public NewBlogVO() {
    }

    public Long getId() {
        return id;
    }

    public NewBlogVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewBlogVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public NewBlogVO setPrivacy(Boolean privacy) {
        this.privacy = privacy;
        return this;
    }

    @Override
    public String toString() {
        return "NewBlogVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", privacy=" + privacy +
                '}';
    }
}
