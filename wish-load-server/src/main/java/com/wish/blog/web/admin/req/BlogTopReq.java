package com.wish.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 */
public class BlogTopReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 2700403591743778154L;

    private Long id;
    private Boolean top;

    public Long getId() {
        return id;
    }

    public BlogTopReq setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getTop() {
        return top;
    }

    public BlogTopReq setTop(Boolean top) {
        this.top = top;
        return this;
    }

    @Override
    public String toString() {
        return "BlogTopReq{" +
                "id=" + id +
                ", top=" + top +
                '}';
    }
}
