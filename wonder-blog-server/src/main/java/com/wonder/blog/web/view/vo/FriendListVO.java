package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class FriendListVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -2901616041395511085L;
    
    private Long id;
    private String nickname;
    private String description;
    private String website;
    private String avatar;
    
    public Long getId() {
        return id;
    }
    
    public FriendListVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public FriendListVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public FriendListVO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public FriendListVO setWebsite(String website) {
        this.website = website;
        return this;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public FriendListVO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendListVO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
