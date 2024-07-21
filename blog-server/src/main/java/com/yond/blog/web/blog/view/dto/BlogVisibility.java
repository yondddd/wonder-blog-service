package com.yond.blog.web.blog.view.dto;

/**
 * @Description: 博客可见性DTO
 * @Author: Naccl
 * @Date: 2020-09-04
 */
public class BlogVisibility {
    private Boolean appreciation;//赞赏开关
    private Boolean recommend;//推荐开关
    private Boolean commentEnabled;//评论开关
    private Boolean top;//是否置顶
    private Boolean published;//公开或私密
    private String password;//密码保护

    public BlogVisibility() {
    }

    public Boolean getAppreciation() {
        return this.appreciation;
    }

    public Boolean getRecommend() {
        return this.recommend;
    }

    public Boolean getCommentEnabled() {
        return this.commentEnabled;
    }

    public Boolean getTop() {
        return this.top;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public void setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "BlogVisibility(appreciation=" + this.getAppreciation() + ", recommend=" + this.getRecommend() + ", commentEnabled=" + this.getCommentEnabled() + ", top=" + this.getTop() + ", published=" + this.getPublished() + ", password=" + this.getPassword() + ")";
    }
}
