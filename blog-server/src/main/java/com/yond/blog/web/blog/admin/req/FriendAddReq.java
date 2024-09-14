package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class FriendAddReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -4974807593476191357L;
    
    private String nickname;
    private String description;
    private String website;
    private String avatar;
    private Boolean published;
    
    public String getNickname() {
        return nickname;
    }
    
    public FriendAddReq setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public FriendAddReq setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public FriendAddReq setWebsite(String website) {
        this.website = website;
        return this;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public FriendAddReq setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public FriendAddReq setPublished(Boolean published) {
        this.published = published;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendAddReq{" +
                "nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", avatar='" + avatar + '\'' +
                ", published=" + published +
                '}';
    }
}
