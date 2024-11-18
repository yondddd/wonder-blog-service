package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 11/18/2024
 * @description
 */
public class FileSpaceReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -4364794633503857436L;

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileSpaceReq{" +
                "path='" + path + '\'' +
                '}';
    }
}
