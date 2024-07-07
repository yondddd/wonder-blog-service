package com.yond.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 友链
 * @Author: Naccl
 * @Date: 2020-09-08
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4190032465499825315L;

    private Long id;
    private String nickname;//昵称
    private String description;//描述
    private String website;//站点
    private String avatar;//头像
    private Boolean published;//公开或隐藏
    private Integer views;//浏览次数
    private Date createTime;//创建时间
}
