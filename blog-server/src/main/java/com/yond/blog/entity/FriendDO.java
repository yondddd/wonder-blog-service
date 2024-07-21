package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 友链
 * @Author: Naccl
 * @Date: 2020-09-08
 */
public class FriendDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4190032465499825315L;

    private Long id;
    private String nickname;//昵称
    private String description;//描述
    private String website;//站点
    private String avatar;//头像
    private Boolean published;//公开或隐藏
    private Integer views;//浏览次数
    private Date createTime;//创建时间

    public FriendDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getDescription() {
        return this.description;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public Integer getViews() {
        return this.views;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "FriendDO(id=" + this.getId() + ", nickname=" + this.getNickname() + ", description=" + this.getDescription() + ", website=" + this.getWebsite() + ", avatar=" + this.getAvatar() + ", published=" + this.getPublished() + ", views=" + this.getViews() + ", createTime=" + this.getCreateTime() + ")";
    }
}
