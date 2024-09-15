package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Yond
 * @date 8/19/2024
 * @description
 */
public class BlogVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5934819917336555995L;

    private Long id;
    private CategoryVO category;
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
    private List<TagVO> tags;

    public Long getId() {
        return id;
    }

    public BlogVO setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryVO getCategory() {
        return category;
    }

    public BlogVO setCategory(CategoryVO category) {
        this.category = category;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public BlogVO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BlogVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public BlogVO setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BlogVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BlogVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public BlogVO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public BlogVO setRecommend(Boolean recommend) {
        this.recommend = recommend;
        return this;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public BlogVO setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
        return this;
    }

    public Boolean getCommentEnabled() {
        return commentEnabled;
    }

    public BlogVO setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }

    public Boolean getTop() {
        return top;
    }

    public BlogVO setTop(Boolean top) {
        this.top = top;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BlogVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BlogVO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public BlogVO setViews(Integer views) {
        this.views = views;
        return this;
    }

    public Integer getWords() {
        return words;
    }

    public BlogVO setWords(Integer words) {
        this.words = words;
        return this;
    }

    public Integer getReadTime() {
        return readTime;
    }

    public BlogVO setReadTime(Integer readTime) {
        this.readTime = readTime;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BlogVO setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public BlogVO setTags(List<TagVO> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "BlogVO{" +
                "id=" + id +
                ", category=" + category +
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
                ", tags=" + tags +
                '}';
    }
}
