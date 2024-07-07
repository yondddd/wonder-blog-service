package com.yond.blog.web.blog.view.vo;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客详情
 * @Author: Naccl
 * @Date: 2020-08-12
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 3673283593087693026L;
    private Long id;
    private String title;//文章标题
    private String content;//文章正文
    private Boolean appreciation;//赞赏开关
    private Boolean commentEnabled;//评论开关
    private Boolean top;//是否置顶
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer views;//浏览次数
    private Integer words;//文章字数
    private Integer readTime;//阅读时长(分钟)
    private String password;//密码保护

    private CategoryDO category;//文章分类
    private List<TagDO> tags = new ArrayList<>();//文章标签
}
