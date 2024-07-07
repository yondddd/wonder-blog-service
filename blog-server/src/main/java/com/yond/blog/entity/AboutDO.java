package com.yond.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 关于我
 * @Author: Naccl
 * @Date: 2020-08-31
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AboutDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7160388998935748571L;

    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;

}
