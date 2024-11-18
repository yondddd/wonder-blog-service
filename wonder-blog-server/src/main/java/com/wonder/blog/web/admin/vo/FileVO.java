package com.wonder.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 11/18/2024
 * @description
 */
public class FileVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4755084586807896928L;

    private boolean folder;
    private String name;
    private String url;

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileVO{" +
                "folder=" + folder +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
