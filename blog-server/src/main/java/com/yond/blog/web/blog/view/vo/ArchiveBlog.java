package com.yond.blog.web.blog.view.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 归档页面博客简要信息
 * @Author: Naccl
 * @Date: 2020-08-12
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArchiveBlog implements Serializable {
    @Serial
    private static final long serialVersionUID = -846138106510742666L;
    private Long id;
    private String title;
    private String day;
    private String password;
    private Boolean privacy;
}
