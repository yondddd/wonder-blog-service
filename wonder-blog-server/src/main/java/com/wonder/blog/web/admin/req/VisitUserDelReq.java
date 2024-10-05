package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yond
 */
public class VisitUserDelReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -5140519488374252701L;

    private Long id;
    private String uuid;

    public Long getId() {
        return id;
    }

    public VisitUserDelReq setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public VisitUserDelReq setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @Override
    public String toString() {
        return "VisitUserDelReq{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
