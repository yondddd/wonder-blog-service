package com.yond.blog.web.view.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 发表评论请求
 * @Author: Yond
 */
public class CommentLeaveReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 3482175003266432333L;
    
    private Integer page;
    private Long blogId;
    private Long parentId;
    private String nickname;
    private String qq;
    private String email;
    private String content;
    private String avatar;
    private String website;
    private Boolean notice;
    
    public Integer getPage() {
        return page;
    }
    
    public CommentLeaveReq setPage(Integer page) {
        this.page = page;
        return this;
    }
    
    public Long getBlogId() {
        return blogId;
    }
    
    public CommentLeaveReq setBlogId(Long blogId) {
        this.blogId = blogId;
        return this;
    }
    
    public Long getParentId() {
        return parentId;
    }
    
    public CommentLeaveReq setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public CommentLeaveReq setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public String getQq() {
        return qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
    
    public String getEmail() {
        return email;
    }
    
    public CommentLeaveReq setEmail(String email) {
        this.email = email;
        return this;
    }
    
    public String getContent() {
        return content;
    }
    
    public CommentLeaveReq setContent(String content) {
        this.content = content;
        return this;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public CommentLeaveReq setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public CommentLeaveReq setWebsite(String website) {
        this.website = website;
        return this;
    }
    
    public Boolean getNotice() {
        return notice;
    }
    
    public CommentLeaveReq setNotice(Boolean notice) {
        this.notice = notice;
        return this;
    }
    
    @Override
    public String toString() {
        return "CommentLeaveReq{" +
                "page=" + page +
                ", blogId=" + blogId +
                ", parentId=" + parentId +
                ", nickname='" + nickname + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", website='" + website + '\'' +
                ", notice=" + notice +
                '}';
    }
}
