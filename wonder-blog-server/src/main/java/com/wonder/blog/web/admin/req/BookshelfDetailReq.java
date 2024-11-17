package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 11/17/2024
 * @description
 */
public class BookshelfDetailReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 4581664134486058452L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookshelfDetailReq{" +
                "id=" + id +
                '}';
    }
}
