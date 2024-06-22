package com.yond.entity;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
