package com.yond.blog.web.blog.view.dto;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.entity.UserDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客DTO
 * @Author: Naccl
 * @Date: 2020-08-27
 */
public class Blog {
    private Long id;
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

    private UserDO user;//文章作者(因为是个人博客，也可以不加作者字段，暂且加上)
    private CategoryDO category;//文章分类
    private List<TagDO> tags = new ArrayList<>();//文章标签

    private Object cate;//页面展示层传输的分类对象：正常情况下为 字符串 或 分类id
    private List<Object> tagList;//页面展示层传输的标签对象：正常情况下为 List<Integer>标签id 或 List<String>标签名

    public Blog() {
    }

    public Long getId() {
        return this.id;
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

    public UserDO getUser() {
        return this.user;
    }

    public CategoryDO getCategory() {
        return this.category;
    }

    public List<TagDO> getTags() {
        return this.tags;
    }

    public Object getCate() {
        return this.cate;
    }

    public List<Object> getTagList() {
        return this.tagList;
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

    public void setUser(UserDO user) {
        this.user = user;
    }

    public void setCategory(CategoryDO category) {
        this.category = category;
    }

    public void setTags(List<TagDO> tags) {
        this.tags = tags;
    }

    public void setCate(Object cate) {
        this.cate = cate;
    }

    public void setTagList(List<Object> tagList) {
        this.tagList = tagList;
    }

    public String toString() {
        return "Blog(id=" + this.getId() + ", title=" + this.getTitle() + ", firstPicture=" + this.getFirstPicture() + ", content=" + this.getContent() + ", description=" + this.getDescription() + ", published=" + this.getPublished() + ", recommend=" + this.getRecommend() + ", appreciation=" + this.getAppreciation() + ", commentEnabled=" + this.getCommentEnabled() + ", top=" + this.getTop() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", views=" + this.getViews() + ", words=" + this.getWords() + ", readTime=" + this.getReadTime() + ", password=" + this.getPassword() + ", user=" + this.getUser() + ", category=" + this.getCategory() + ", tags=" + this.getTags() + ", cate=" + this.getCate() + ", tagList=" + this.getTagList() + ")";
    }
}
