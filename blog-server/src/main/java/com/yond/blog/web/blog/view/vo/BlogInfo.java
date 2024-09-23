package com.yond.blog.web.blog.view.vo;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客简要信息
 * @Author: Yond
 */
public class BlogInfo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -6817827235477579999L;
    
    private Long id;
    private String title;//文章标题
    private String description;//描述
    private Date createTime;//创建时间
    private Integer views;//浏览次数
    private Integer words;//文章字数
    private Integer readTime;//阅读时长(分钟)
    private Boolean top;//是否置顶
    private String password;//文章密码
    private Boolean privacy;//是否私密文章
    
    private CategoryDO category;//文章分类
    private List<TagDO> tags = new ArrayList<>();//文章标签
    
    public BlogInfo() {
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Date getCreateTime() {
        return this.createTime;
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
    
    public Boolean getTop() {
        return this.top;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public Boolean getPrivacy() {
        return this.privacy;
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
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    
    public void setTop(Boolean top) {
        this.top = top;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }
    
    public void setCategory(CategoryDO category) {
        this.category = category;
    }
    
    public void setTags(List<TagDO> tags) {
        this.tags = tags;
    }
    
    public String toString() {
        return "BlogInfo(id=" + this.getId() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", createTime=" + this.getCreateTime() + ", views=" + this.getViews() + ", words=" + this.getWords() + ", readTime=" + this.getReadTime() + ", top=" + this.getTop() + ", password=" + this.getPassword() + ", privacy=" + this.getPrivacy() + ", category=" + this.getCategory() + ", tags=" + this.getTags() + ")";
    }
}
