package com.yond.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 评论管理页面按博客title查询评论
 * @Author: Naccl
 * @Date: 2020-08-03
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogIdAndTitle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1649191154613069726L;
    private Long id;
    private String title;
}
