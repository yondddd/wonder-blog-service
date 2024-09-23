package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: WangJieLong
 */
public class BlogVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 8849471023866558534L;
    
    private Long id;
    private String title;
    private String description;
    private Date createTime;
    private Integer views;
    private Integer words;
    private Integer readTime;
    private Boolean top;
    private String password;
    private Boolean privacy;
    private BlogCategoryVO category;
    private List<BlogTagVO> tagList;
    
    public Long getId() {
        return id;
    }
    
    public BlogVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public BlogVO setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public BlogVO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public BlogVO setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    
    public Boolean getTop() {
        return top;
    }
    
    public BlogVO setTop(Boolean top) {
        this.top = top;
        return this;
    }
    
    public String getPassword() {
        return password;
    }
    
    public BlogVO setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public Boolean getPrivacy() {
        return privacy;
    }
    
    public BlogVO setPrivacy(Boolean privacy) {
        this.privacy = privacy;
        return this;
    }
    
    public BlogCategoryVO getCategory() {
        return category;
    }
    
    public BlogVO setCategory(BlogCategoryVO category) {
        this.category = category;
        return this;
    }
    
    public List<BlogTagVO> getTagList() {
        return tagList;
    }
    
    public BlogVO setTagList(List<BlogTagVO> tagList) {
        this.tagList = tagList;
        return this;
    }
    
    @Override
    public String toString() {
        return "BlogVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", views=" + views +
                ", words=" + words +
                ", readTime=" + readTime +
                ", top=" + top +
                ", password='" + password + '\'' +
                ", privacy=" + privacy +
                ", category=" + category +
                ", tagList=" + tagList +
                '}';
    }
}
