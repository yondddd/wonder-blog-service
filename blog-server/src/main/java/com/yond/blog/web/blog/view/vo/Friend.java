package com.yond.blog.web.blog.view.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 友链VO
 * @Author: Naccl
 * @Date: 2020-09-08
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Friend implements Serializable {
    @Serial
    private static final long serialVersionUID = 5662086638296526186L;
    private String nickname;//昵称
    private String description;//描述
    private String website;//站点
    private String avatar;//头像
}
