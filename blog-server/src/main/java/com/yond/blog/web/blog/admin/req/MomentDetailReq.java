package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 * @date 8/31/2024
 * @description
 */
public class MomentDetailReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -8566609279949703332L;

    private Long id;

    public Long getId() {
        return id;
    }

    public MomentDetailReq setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "MomentDetailReq{" +
                "id=" + id +
                '}';
    }
}
