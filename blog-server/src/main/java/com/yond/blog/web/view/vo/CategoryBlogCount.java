package com.yond.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 分类和博客数量
 * @Author: Yond
 */
public class CategoryBlogCount implements Serializable {
    @Serial
    private static final long serialVersionUID = 4056358458356170978L;
    private Long id;
    private String name;//分类名
    private Integer value;//分类下博客数量
    
    public CategoryBlogCount() {
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
        return "CategoryBlogCount(id=" + this.getId() + ", name=" + this.getName() + ", value=" + this.getValue() + ")";
    }
}
