package com.yond.blog.web.blog.view.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 自定义爱好
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Favorite implements Serializable {
    @Serial
    private static final long serialVersionUID = 1003846806944482693L;
    private String title;
    private String content;
}
