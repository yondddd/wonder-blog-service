package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 11/16/2024
 * @description
 */
public class BookshelfDelReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -2785842624342550372L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookshelfDelReq{" +
                "id=" + id +
                '}';
    }
}
