package com.yond.blog.web.admin.req;


import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 */
public class BlogDelReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -8368564315154107392L;

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
