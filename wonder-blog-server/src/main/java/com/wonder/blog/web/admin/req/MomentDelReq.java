package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 */
public class MomentDelReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -4700506182047419059L;

    private Long id;

    public Long getId() {
        return id;
    }

    public MomentDelReq setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "MomentDelReq{" +
                "id=" + id +
                '}';
    }
}
