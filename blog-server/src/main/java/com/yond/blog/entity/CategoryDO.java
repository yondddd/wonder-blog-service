package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 博客分类
 * @Author: Naccl
 * @Date: 2020-07-26
 */
public class CategoryDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2214374622963598886L;

    /**
     * 分类id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;

    public static CategoryDO custom() {
        return new CategoryDO();
    }

    public CategoryDO() {
    }

    public Long getId() {
        return id;
    }

    public CategoryDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
