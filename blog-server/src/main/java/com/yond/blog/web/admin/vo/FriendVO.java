package com.yond.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yond
 */
public class FriendVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 6311125875955281552L;
    
    private Long id;
    private String nickname;
    private String description;
    private String website;
    private String avatar;
    private Boolean published;
    private Integer views;
    private Date createTime;
    
    public Long getId() {
        return id;
    }
    
    public FriendVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public FriendVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public FriendVO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public FriendVO setWebsite(String website) {
        this.website = website;
        return this;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public FriendVO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public FriendVO setPublished(Boolean published) {
        this.published = published;
        return this;
    }
    
    public Integer getViews() {
        return views;
    }
    
    public FriendVO setViews(Integer views) {
        this.views = views;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public FriendVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    @Override
    public String toString() {
        String sb = "FriendVO{" + "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", avatar='" + avatar + '\'' +
                ", published=" + published +
                ", views=" + views +
                ", createTime=" + createTime +
                '}';
        return sb;
    }
}
