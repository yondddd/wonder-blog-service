package com.wonder.blog.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 博客标签
 * @Author: Yond
 */
public class TagDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -19713361186882677L;

    private Long id;
    private String name;//标签名称
    private String color;//标签颜色(与Semantic UI提供的颜色对应，可选)

    public TagDO() {
    }

    public static TagDO custom() {
        return new TagDO();
    }

    public Long getId() {
        return id;
    }

    public TagDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public TagDO setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public String toString() {
        return "TagDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
