package com.yond.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 */
public class TagVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4288642746044002755L;

    private Long id;
    private String name;
    private String color;

    public Long getId() {
        return id;
    }

    public TagVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public TagVO setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public String toString() {
        return "TagVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
