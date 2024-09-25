package com.yond.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
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
