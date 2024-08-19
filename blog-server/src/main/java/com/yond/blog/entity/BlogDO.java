package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客文章
 * @Author: Naccl
 * @Date: 2020-07-26
 */
public class BlogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5799826828777919864L;

    private Long id;
    private Integer categoryId;
    private Integer userId;
    private String title;//文章标题
    private String firstPicture;//文章首图，用于随机文章展示
    private String content;//文章正文
    private String description;//描述
    private Boolean published;//公开或私密
    private Boolean recommend;//推荐开关
    private Boolean appreciation;//赞赏开关
    private Boolean commentEnabled;//评论开关
    private Boolean top;//是否置顶
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer views;//浏览次数
    private Integer words;//文章字数
    private Integer readTime;//阅读时长(分钟)
    private String password;//密码保护

    public BlogDO() {
    }

    public Long getId() {
        return this.id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getFirstPicture() {
        return this.firstPicture;
    }

    public String getContent() {
        return this.content;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public Boolean getRecommend() {
        return this.recommend;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
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

    @Override
    public String toString() {
        return "BlogDO{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", recommend=" + recommend +
                ", appreciation=" + appreciation +
                ", commentEnabled=" + commentEnabled +
                ", top=" + top +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", views=" + views +
                ", words=" + words +
                ", readTime=" + readTime +
                ", password='" + password + '\'' +
                '}';
    }
}
