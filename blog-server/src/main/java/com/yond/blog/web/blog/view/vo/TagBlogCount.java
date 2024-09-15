package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 标签和博客数量
 * @Author: Yond
 */
public class TagBlogCount implements Serializable {
    @Serial
    private static final long serialVersionUID = -3257412587322008374L;
    private Long id;
    private String name;//标签名
    private Integer value;//标签下博客数量

    public TagBlogCount() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String toString() {
        return "TagBlogCount(id=" + this.getId() + ", name=" + this.getName() + ", value=" + this.getValue() + ")";
    }
}
