package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 * @date 9/1/2024
 * @description
 */
public class TagDelReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -3908498284372366158L;

    private Long id;

    public Long getId() {
        return id;
    }

    public TagDelReq setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "TagDelReq{" +
                "id=" + id +
                '}';
    }
}
