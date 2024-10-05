package com.wonder.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 友链
 * @Author: Yond
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
    private Integer status;
    
    public static FriendDO custom() {
        return new FriendDO();
    }
    
    public FriendDO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public FriendDO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public FriendDO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public FriendDO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public FriendDO setWebsite(String website) {
        this.website = website;
        return this;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public FriendDO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public FriendDO setPublished(Boolean published) {
        this.published = published;
        return this;
    }
    
    public Integer getViews() {
        return views;
    }
    
    public FriendDO setViews(Integer views) {
        this.views = views;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public FriendDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public FriendDO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendDO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", avatar='" + avatar + '\'' +
                ", published=" + published +
                ", views=" + views +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
