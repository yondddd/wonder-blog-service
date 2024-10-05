package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yond
 */
public class CommentUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 6721947163363283925L;


    private Long id;
    private String nickname;
    private String avatar;
    private String email;
    private String website;
    private String ip;
    private String content;

    public Long getId() {
        return id;
    }

    public CommentUpdateReq setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public CommentUpdateReq setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public CommentUpdateReq setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CommentUpdateReq setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CommentUpdateReq setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public CommentUpdateReq setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentUpdateReq setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "CommentUpdateReq{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", ip='" + ip + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
