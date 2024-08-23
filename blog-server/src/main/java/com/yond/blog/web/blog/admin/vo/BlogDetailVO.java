package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yond
 * @date 8/22/2024
 * @description
 */
public class BlogDetailVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1654007367041602585L;

    private Long id;
    private Integer categoryId;
    private String categoryName;
    private Integer userId;
    private String title;
    private String firstPicture;
    private String content;
    private String description;
    private Boolean published;
    private Boolean recommend;
    private Boolean appreciation;
    private Boolean commentEnabled;
    private Boolean top;
    private Date createTime;
    private Date updateTime;
    private Integer views;
    private Integer words;
    private Integer readTime;
    private String password;
    private List<TagVO> tagList;

    public Long getId() {
        return id;
    }

    public BlogDetailVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public BlogDetailVO setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public BlogDetailVO setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public BlogDetailVO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BlogDetailVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public BlogDetailVO setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BlogDetailVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BlogDetailVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public BlogDetailVO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public BlogDetailVO setRecommend(Boolean recommend) {
        this.recommend = recommend;
        return this;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public BlogDetailVO setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
        return this;
    }

    public Boolean getCommentEnabled() {
        return commentEnabled;
    }

    public BlogDetailVO setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }

    public Boolean getTop() {
        return top;
    }

    public BlogDetailVO setTop(Boolean top) {
        this.top = top;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BlogDetailVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BlogDetailVO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public BlogDetailVO setViews(Integer views) {
        this.views = views;
        return this;
    }

    public Integer getWords() {
        return words;
    }

    public BlogDetailVO setWords(Integer words) {
        this.words = words;
        return this;
    }

    public Integer getReadTime() {
        return readTime;
    }

    public BlogDetailVO setReadTime(Integer readTime) {
        this.readTime = readTime;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BlogDetailVO setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<TagVO> getTagList() {
        return tagList;
    }

    public BlogDetailVO setTagList(List<TagVO> tagList) {
        this.tagList = tagList;
        return this;
    }

    @Override
    public String toString() {
        return "BlogDetailVO{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
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
                ", tagList=" + tagList +
                '}';
    }
}
