package com.yond.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 关键字搜索博客
 * @Author: Naccl
 * @Date: 2020-09-06
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchBlog implements Serializable {
    @Serial
    private static final long serialVersionUID = -154517852364468455L;
    private Long id;
    private String title;
    private String content;
}
