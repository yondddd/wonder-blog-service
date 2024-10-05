package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class BlogTagVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1473392158975730287L;

    private Long id;
    private String name;
    private String color;

    public Long getId() {
        return id;
    }

    public BlogTagVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BlogTagVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public BlogTagVO setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public String toString() {
        return "BlogTagVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
