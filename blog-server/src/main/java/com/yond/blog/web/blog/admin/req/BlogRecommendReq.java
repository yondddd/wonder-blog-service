package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 * @date 8/22/2024
 * @description
 */
public class BlogRecommendReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 4464197809107269369L;

    private Long id;
    private Boolean recommend;

    public Long getId() {
        return id;
    }

    public BlogRecommendReq setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public BlogRecommendReq setRecommend(Boolean recommend) {
        this.recommend = recommend;
        return this;
    }

    @Override
    public String toString() {
        return "BlogRecommendReq{" +
                "id=" + id +
                ", recommend=" + recommend +
                '}';
    }
}
