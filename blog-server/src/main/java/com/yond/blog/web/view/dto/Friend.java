package com.yond.blog.web.view.dto;

/**
 * @Description: 友链DTO
 * @Author: Yond
 */
public class Friend {
    private Long id;
    private String nickname;//昵称
    private String description;//描述
    private String website;//站点
    private String avatar;//头像
    private Boolean published;//公开或隐藏
    
    public Friend() {
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
    
    public String toString() {
        return "Friend(id=" + this.getId() + ", nickname=" + this.getNickname() + ", description=" + this.getDescription() + ", website=" + this.getWebsite() + ", avatar=" + this.getAvatar() + ", published=" + this.getPublished() + ")";
    }
}
