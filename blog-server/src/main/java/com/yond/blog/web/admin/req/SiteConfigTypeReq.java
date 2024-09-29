package com.yond.blog.web.admin.req;

import com.yond.common.enums.SiteConfigTypeEnum;

import java.io.Serial;
import java.io.Serializable;

public class SiteConfigTypeReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 4681010057748916853L;

    /**
     * {@link SiteConfigTypeEnum}
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public SiteConfigTypeReq setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "SiteConfigTypeReq{" +
                "type=" + type +
                '}';
    }
}
