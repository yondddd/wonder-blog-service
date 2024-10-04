package com.wish.blog.entity;

import com.wish.common.enums.EnableStatusEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客文章
 * @Author: Yond
 */
public class BlogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5799826828777919864L;

    public static BlogDO custom() {
        return new BlogDO();
    }

    private Long id;
    private Long categoryId;
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
    /**
     * {@link EnableStatusEnum}
     */
    private Integer status;

    public BlogDO() {
    }

    public String getTitle() {
        return title;
    }

    public BlogDO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BlogDO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public BlogDO setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public BlogDO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public BlogDO setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BlogDO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BlogDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public BlogDO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public BlogDO setRecommend(Boolean recommend) {
        this.recommend = recommend;
        return this;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public BlogDO setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
        return this;
    }

    public Boolean getCommentEnabled() {
        return commentEnabled;
    }

    public BlogDO setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }

    public Boolean getTop() {
        return top;
    }

    public BlogDO setTop(Boolean top) {
        this.top = top;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BlogDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BlogDO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public BlogDO setViews(Integer views) {
        this.views = views;
        return this;
    }

    public Integer getWords() {
        return words;
    }

    public BlogDO setWords(Integer words) {
        this.words = words;
        return this;
    }

    public Integer getReadTime() {
        return readTime;
    }

    public BlogDO setReadTime(Integer readTime) {
        this.readTime = readTime;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BlogDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BlogDO setStatus(Integer status) {
        this.status = status;
        return this;
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
                ", status=" + status +
                '}';
    }
}
