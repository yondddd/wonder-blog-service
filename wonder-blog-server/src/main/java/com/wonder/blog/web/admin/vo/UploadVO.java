package com.wonder.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 11/16/2024
 * @description upload response
 */
public class UploadVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1209551482687316423L;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UploadVO{" +
                "url='" + url + '\'' +
                '}';
    }
}
