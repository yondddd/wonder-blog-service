package com.yond.blog.web.blog.view.vo;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;

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

    public BlogDetail() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Boolean getAppreciation() {
        return this.appreciation;
    }

    public Boolean getCommentEnabled() {
        return this.commentEnabled;
    }

    public Boolean getTop() {
        return this.top;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Integer getViews() {
        return this.views;
    }

    public Integer getWords() {
        return this.words;
    }

    public Integer getReadTime() {
        return this.readTime;
    }

    public String getPassword() {
        return this.password;
    }

    public CategoryDO getCategory() {
        return this.category;
    }

    public List<TagDO> getTags() {
        return this.tags;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public void setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public void setReadTime(Integer readTime) {
        this.readTime = readTime;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCategory(CategoryDO category) {
        this.category = category;
    }

    public void setTags(List<TagDO> tags) {
        this.tags = tags;
    }

    public String toString() {
        return "BlogDetail(id=" + this.getId() + ", title=" + this.getTitle() + ", content=" + this.getContent() + ", appreciation=" + this.getAppreciation() + ", commentEnabled=" + this.getCommentEnabled() + ", top=" + this.getTop() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", views=" + this.getViews() + ", words=" + this.getWords() + ", readTime=" + this.getReadTime() + ", password=" + this.getPassword() + ", category=" + this.getCategory() + ", tags=" + this.getTags() + ")";
    }
}
