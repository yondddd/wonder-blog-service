package com.wish.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class FriendUpdateReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 3227103913416954449L;
    
    private Long id;
    private String nickname;
    private String description;
    private String website;
    private String avatar;
    private Boolean published;
    
    public Long getId() {
        return id;
    }
    
    public FriendUpdateReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public FriendUpdateReq setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public FriendUpdateReq setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public FriendUpdateReq setWebsite(String website) {
        this.website = website;
        return this;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public FriendUpdateReq setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public FriendUpdateReq setPublished(Boolean published) {
        this.published = published;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendUpdateReq{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", avatar='" + avatar + '\'' +
                ", published=" + published +
                '}';
    }
}
