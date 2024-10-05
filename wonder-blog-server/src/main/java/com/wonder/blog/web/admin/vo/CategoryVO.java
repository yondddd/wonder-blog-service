package com.wonder.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Yond
 */
public class CategoryVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2072646709015076229L;

    /**
     * 分类id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;

    public static CategoryVO custom() {
        return new CategoryVO();
    }

    public Long getId() {
        return id;
    }

    public CategoryVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryVO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
