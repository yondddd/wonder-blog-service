package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客评论
 * @Author: Yond
 * @Date: 2020-07-27
 */
public class CommentDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4679382704395889186L;

    private Long id;
    private Integer page;//0普通文章，1关于我页面
    private Long blogId; // 业务key比如blogId
    private Long parentId;//父评论id
    private String nickname;//昵称
    private String email;//邮箱
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    private String website;//个人网站
    private String ip;//评论者ip地址
    private Boolean published;//公开或回收站
    private Boolean adminComment;//博主回复
    private Boolean notice;//接收邮件提醒
    private String qq;//如果评论昵称为QQ号，则将昵称和头像置为QQ昵称和QQ头像，并将此字段置为QQ号备份
    private Integer status;
    private Date createTime;//评论时间

    public CommentDO() {
    }

    public static CommentDO custom() {
        return new CommentDO();
    }

    public Long getId() {
        return id;
    }

    public CommentDO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CommentDO setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Long getBlogId() {
        return blogId;
    }

    public CommentDO setBlogId(Long blogId) {
        this.blogId = blogId;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public CommentDO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public CommentDO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CommentDO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentDO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public CommentDO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CommentDO setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public CommentDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public CommentDO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public CommentDO setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
        return this;
    }

    public Boolean getNotice() {
        return notice;
    }

    public CommentDO setNotice(Boolean notice) {
        this.notice = notice;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public CommentDO setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CommentDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CommentDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "CommentDO{" +
                "id=" + id +
                ", page=" + page +
                ", blogId=" + blogId +
                ", parentId=" + parentId +
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
                '}';
    }
}
