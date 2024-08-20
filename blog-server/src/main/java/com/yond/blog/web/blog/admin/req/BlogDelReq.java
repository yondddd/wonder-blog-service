package com.yond.blog.web.blog.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 8/20/2024
 * @description
 */
public class BlogDelReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -8368564315154107392L;

    @Schema()
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BlogDelReq{" +
                "id=" + id +
                '}';
    }
}
