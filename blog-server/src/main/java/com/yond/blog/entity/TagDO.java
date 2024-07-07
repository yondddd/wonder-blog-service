package com.yond.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 博客标签
 * @Author: Naccl
 * @Date: 2020-07-27
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TagDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -19713361186882677L;
    private Long id;
    private String name;//标签名称
    private String color;//标签颜色(与Semantic UI提供的颜色对应，可选)
    private List<BlogDO> blogs = new ArrayList<>();//该标签下的博客文章
}
