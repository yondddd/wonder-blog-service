package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class BlogCategoryVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 834527657208277137L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public BlogCategoryVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BlogCategoryVO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "BlogCategoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
