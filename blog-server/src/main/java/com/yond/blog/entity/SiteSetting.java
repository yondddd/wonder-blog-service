package com.yond.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 站点设置
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteSetting implements Serializable {
    @Serial
    private static final long serialVersionUID = 975624987598974165L;
    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;
    private Integer type;
}
