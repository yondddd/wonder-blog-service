package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 友链VO
 * @Author: Naccl
 * @Date: 2020-09-08
 */
public class Friend implements Serializable {
    @Serial
    private static final long serialVersionUID = 5662086638296526186L;
    private String nickname;//昵称
    private String description;//描述
    private String website;//站点
    private String avatar;//头像

    public Friend() {
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

    public String toString() {
        return "Friend(nickname=" + this.getNickname() + ", description=" + this.getDescription() + ", website=" + this.getWebsite() + ", avatar=" + this.getAvatar() + ")";
    }
}
