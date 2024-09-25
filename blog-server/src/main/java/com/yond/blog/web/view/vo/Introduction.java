package com.yond.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 侧边栏资料卡
 * @Author: Yond
 */
public class Introduction implements Serializable {
    @Serial
    private static final long serialVersionUID = 8908116814963447260L;
    private String avatar;
    private String name;
    private String github;
    private String telegram;
    private String qq;
    private String bilibili;
    private String netease;
    private String email;
    
    private List<String> rollText = new ArrayList<>();
    private List<Favorite> favorites = new ArrayList<>();
    
    public Introduction() {
    }
    
    public String getAvatar() {
        return this.avatar;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGithub() {
        return this.github;
    }
    
    public String getTelegram() {
        return this.telegram;
    }
    
    public String getQq() {
        return this.qq;
    }
    
    public String getBilibili() {
        return this.bilibili;
    }
    
    public String getNetease() {
        return this.netease;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public List<String> getRollText() {
        return this.rollText;
    }
    
    public List<Favorite> getFavorites() {
        return this.favorites;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGithub(String github) {
        this.github = github;
    }
    
    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
    
    public void setBilibili(String bilibili) {
        this.bilibili = bilibili;
    }
    
    public void setNetease(String netease) {
        this.netease = netease;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setRollText(List<String> rollText) {
        this.rollText = rollText;
    }
    
    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
    
    public String toString() {
        return "Introduction(avatar=" + this.getAvatar() + ", name=" + this.getName() + ", github=" + this.getGithub() + ", telegram=" + this.getTelegram() + ", qq=" + this.getQq() + ", bilibili=" + this.getBilibili() + ", netease=" + this.getNetease() + ", email=" + this.getEmail() + ", rollText=" + this.getRollText() + ", favorites=" + this.getFavorites() + ")";
    }
}
