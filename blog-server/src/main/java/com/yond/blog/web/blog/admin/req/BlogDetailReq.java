package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 * @date 8/22/2024
 * @description
 */
public class BlogDetailReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 7995935245819133598L;

    private Long id;

    public Long getId() {
        return id;
    }

    public BlogDetailReq setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "BlogDetailReq{" +
                "id=" + id +
                '}';
    }
}
