package com.wonder.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class BlogBaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -279611872756119421L;

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public BlogBaseVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BlogBaseVO setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        String sb = "BlogBaseVO{" + "id=" + id +
                ", title='" + title + '\'' +
                '}';
        return sb;
    }
}
