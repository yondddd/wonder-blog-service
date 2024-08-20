package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 8/20/2024
 * @description
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
        return "CategoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
