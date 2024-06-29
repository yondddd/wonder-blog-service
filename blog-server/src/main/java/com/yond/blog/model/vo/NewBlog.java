package com.yond.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 最新推荐博客
 * @Author: Naccl
 * @Date: 2020-09-05
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewBlog implements Serializable {
    @Serial
    private static final long serialVersionUID = -6188444658536941480L;
    private Long id;
    private String title;
    private String password;
    private Boolean privacy;
}
