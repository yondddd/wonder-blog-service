package com.wish.blog.web.admin.dto;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Yond
 */
public class CommentDTO {

    private Long id;
    private Integer page;
    private Long blogId;
    private Long parentId;
    private String parentNickname;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private String website;
    private String ip;
    private Boolean published;
    private Boolean adminComment;
    private Boolean notice;
    private String qq;
    private Integer status;
    private Date createTime;
    private List<CommentDTO> reply;

    public Long getId() {
        return id;
    }

    public CommentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CommentDTO setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Long getBlogId() {
        return blogId;
    }

    public CommentDTO setBlogId(Long blogId) {
        this.blogId = blogId;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public CommentDTO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getParentNickname() {
        return parentNickname;
    }

    public CommentDTO setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public CommentDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CommentDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public CommentDTO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CommentDTO setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public CommentDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public CommentDTO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public CommentDTO setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
        return this;
    }

    public Boolean getNotice() {
        return notice;
    }

    public CommentDTO setNotice(Boolean notice) {
        this.notice = notice;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public CommentDTO setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CommentDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CommentDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<CommentDTO> getReply() {
        return reply;
    }

    public CommentDTO setReply(List<CommentDTO> reply) {
        this.reply = reply;
        return this;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", page=" + page +
                ", blogId=" + blogId +
                ", parentId=" + parentId +
                ", parentNickname='" + parentNickname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", website='" + website + '\'' +
                ", ip='" + ip + '\'' +
                ", published=" + published +
                ", adminComment=" + adminComment +
                ", notice=" + notice +
                ", qq='" + qq + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", reply=" + reply +
                '}';
    }
}
