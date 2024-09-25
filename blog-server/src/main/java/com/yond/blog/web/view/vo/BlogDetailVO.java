package com.yond.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客详情
 * @Author: Yond
 */
public class BlogDetailVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 3673283593087693026L;
    
    private Long id;
    private String title;
    private String content;
    private Boolean appreciation;
    private Boolean commentEnabled;
    private Boolean top;
    private Date createTime;
    private Date updateTime;
    private Integer views;
    private Integer words;
    private Integer readTime;
    
    private BlogCategoryVO category;
    private List<BlogTagVO> tags = new ArrayList<>();
    
    public BlogDetailVO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public BlogDetailVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public BlogDetailVO setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getContent() {
        return content;
    }
    
    public BlogDetailVO setContent(String content) {
        this.content = content;
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
    
    public BlogCategoryVO getCategory() {
        return category;
    }
    
    public BlogDetailVO setCategory(BlogCategoryVO category) {
        this.category = category;
        return this;
    }
    
    public List<BlogTagVO> getTags() {
        return tags;
    }
    
    public BlogDetailVO setTags(List<BlogTagVO> tags) {
        this.tags = tags;
        return this;
    }
    
    @Override
    public String toString() {
        return "BlogDetailVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", appreciation=" + appreciation +
                ", commentEnabled=" + commentEnabled +
                ", top=" + top +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", views=" + views +
                ", words=" + words +
                ", readTime=" + readTime +
                ", category=" + category +
                ", tags=" + tags +
                '}';
    }
}
