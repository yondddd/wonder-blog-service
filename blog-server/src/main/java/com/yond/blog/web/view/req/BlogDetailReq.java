package com.yond.blog.web.view.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 * @Date: 2024-09-25
 */
public class BlogDetailReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 2027212715161009612L;

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
