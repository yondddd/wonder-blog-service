package com.wonder.blog.web.admin.req;

import com.wonder.blog.web.admin.vo.CategoryVO;
import com.wonder.blog.web.admin.vo.TagVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author Yond
 */
public class BlogSaveReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 7424474185618004126L;

    private Long id;
    private String title;
    private String firstPicture;
    private String content;
    private String description;
    private Boolean published;
    private Boolean recommend;
    private Boolean appreciation;
    private Boolean commentEnabled;
    private Boolean top;
    private Integer views;
    private Integer words;
    private Integer readTime;
    private String password;
    private CategoryVO category;
    private List<TagVO> tags;

    public Long getId() {
        return id;
    }

    public BlogSaveReq setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BlogSaveReq setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public BlogSaveReq setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BlogSaveReq setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BlogSaveReq setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public BlogSaveReq setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public BlogSaveReq setRecommend(Boolean recommend) {
        this.recommend = recommend;
        return this;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public BlogSaveReq setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
        return this;
    }

    public Boolean getCommentEnabled() {
        return commentEnabled;
    }

    public BlogSaveReq setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }

    public Boolean getTop() {
        return top;
    }

    public BlogSaveReq setTop(Boolean top) {
        this.top = top;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public BlogSaveReq setViews(Integer views) {
        this.views = views;
        return this;
    }

    public Integer getWords() {
        return words;
    }

    public BlogSaveReq setWords(Integer words) {
        this.words = words;
        return this;
    }

    public Integer getReadTime() {
        return readTime;
    }

    public BlogSaveReq setReadTime(Integer readTime) {
        this.readTime = readTime;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BlogSaveReq setPassword(String password) {
        this.password = password;
        return this;
    }

    public CategoryVO getCategory() {
        return category;
    }

    public BlogSaveReq setCategory(CategoryVO category) {
        this.category = category;
        return this;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public BlogSaveReq setTags(List<TagVO> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "BlogSaveReq{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", recommend=" + recommend +
                ", appreciation=" + appreciation +
                ", commentEnabled=" + commentEnabled +
                ", top=" + top +
                ", views=" + views +
                ", words=" + words +
                ", readTime=" + readTime +
                ", password='" + password + '\'' +
                ", category=" + category +
                ", tags=" + tags +
                '}';
    }
}
