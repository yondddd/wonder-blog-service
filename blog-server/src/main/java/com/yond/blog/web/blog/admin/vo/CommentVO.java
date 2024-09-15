package com.yond.blog.web.blog.admin.vo;

import com.yond.common.enums.CommentPageEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Yond
 * @date 9/1/2024
 * @description
 */
public class CommentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8558927688455265744L;

    private Long id;
    private Long parentId;
    /**
     * 所属页面
     * {@link CommentPageEnum}
     */
    private Integer page;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private String website;
    private String ip;
    private Boolean published;
    private Boolean adminComment;
    private Boolean notice;
    private String qq;
    /**
     * 所属博客
     */
    private Long blogId;
    private String blogTitle;
    private final List<CommentVO> reply = new ArrayList<>();

    public Boolean getPublished() {
        return published;
    }

    public CommentVO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CommentVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public CommentVO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CommentVO setPage(Integer page) {
        this.page = page;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public CommentVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CommentVO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public CommentVO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CommentVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CommentVO setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public CommentVO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public CommentVO setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
        return this;
    }

    public Boolean getNotice() {
        return notice;
    }

    public CommentVO setNotice(Boolean notice) {
        this.notice = notice;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public CommentVO setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public Long getBlogId() {
        return blogId;
    }

    public CommentVO setBlogId(Long blogId) {
        this.blogId = blogId;
        return this;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public CommentVO setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
        return this;
    }

    public List<CommentVO> getReply() {
        return reply;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", page=" + page +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", website='" + website + '\'' +
                ", ip='" + ip + '\'' +
                ", published=" + published +
                ", adminComment=" + adminComment +
                ", notice=" + notice +
                ", qq='" + qq + '\'' +
                ", blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", reply=" + reply +
                '}';
    }

}
