package com.yond.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 分类和博客数量
 * @Author: Naccl
 * @Date: 2020-10-08
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryBlogCount implements Serializable {
    @Serial
    private static final long serialVersionUID = 4056358458356170978L;
    private Long id;
    private String name;//分类名
    private Integer value;//分类下博客数量
}
