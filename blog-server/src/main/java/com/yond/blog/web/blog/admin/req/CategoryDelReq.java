package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 * @date 8/31/2024
 * @description
 */
public class CategoryDelReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -4133413957618438260L;

    private Long id;

    public Long getId() {
        return id;
    }

    public CategoryDelReq setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDelReq{" +
                "id=" + id +
                '}';
    }
}
