package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 * @date 8/22/2024
 * @description
 */
public class BlogVisibleReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -8137323174694546619L;

    private Long id;
    private Boolean appreciation;
    private Boolean recommend;
    private Boolean commentEnabled;
    private Boolean top;
    private Boolean published;
    private String password;

    public Long getId() {
        return id;
    }

    public BlogVisibleReq setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public BlogVisibleReq setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
        return this;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public BlogVisibleReq setRecommend(Boolean recommend) {
        this.recommend = recommend;
        return this;
    }

    public Boolean getCommentEnabled() {
        return commentEnabled;
    }

    public BlogVisibleReq setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }

    public Boolean getTop() {
        return top;
    }

    public BlogVisibleReq setTop(Boolean top) {
        this.top = top;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public BlogVisibleReq setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BlogVisibleReq setPassword(String password) {
        this.password = password;
        return this;
    }


    @Override
    public String toString() {
        return "BlogVisibleReq{" +
                "id=" + id +
                ", appreciation=" + appreciation +
                ", recommend=" + recommend +
                ", commentEnabled=" + commentEnabled +
                ", top=" + top +
                ", published=" + published +
                ", password='" + password + '\'' +
                '}';
    }
}
