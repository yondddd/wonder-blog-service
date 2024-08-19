package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 博客标签
 * @Author: Naccl
 * @Date: 2020-07-27
 */
public class TagDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -19713361186882677L;
    private Long id;
    private String name;//标签名称
    private String color;//标签颜色(与Semantic UI提供的颜色对应，可选)

    public TagDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
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
