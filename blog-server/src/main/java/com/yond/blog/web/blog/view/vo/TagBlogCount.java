package com.yond.blog.web.blog.view.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 标签和博客数量
 * @Author: Naccl
 * @Date: 2020-10-08
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TagBlogCount implements Serializable {
    @Serial
    private static final long serialVersionUID = -3257412587322008374L;
    private Long id;
    private String name;//标签名
    private Integer value;//标签下博客数量
}
