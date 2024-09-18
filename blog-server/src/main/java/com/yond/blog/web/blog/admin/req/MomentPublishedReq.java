package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 */
public class MomentPublishedReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 3728993204554481004L;

    private Long id;
    private Boolean published;

    public Boolean getPublished() {
        return published;
    }

    public MomentPublishedReq setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Long getId() {
        return id;
    }

    public MomentPublishedReq setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "MomentPublishedReq{" +
                "id=" + id +
                ", published=" + published +
                '}';
    }
}
