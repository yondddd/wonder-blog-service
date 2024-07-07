package com.yond.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 博客分类
 * @Author: Naccl
 * @Date: 2020-07-26
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
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

}
