package com.yond.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客动态
 * @Author: Naccl
 * @Date: 2020-08-24
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Moment implements Serializable {

    @Serial
    private static final long serialVersionUID = 7053603743312052909L;

    private Long id;
    private String content;//动态内容
    private Date createTime;//创建时间
    private Integer likes;//点赞数量
    private Boolean published;//是否公开
}
