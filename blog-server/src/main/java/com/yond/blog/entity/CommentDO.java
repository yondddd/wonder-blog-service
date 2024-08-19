package com.yond.blog.entity;

import com.yond.blog.web.blog.view.vo.BlogIdAndTitle;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客评论
 * @Author: Naccl
 * @Date: 2020-07-27
 */
public class CommentDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4679382704395889186L;

    private Long id;
    private String nickname;//昵称
    private String email;//邮箱
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    private Date createTime;//评论时间
    private String website;//个人网站
    private String ip;//评论者ip地址
    private Boolean published;//公开或回收站
    private Boolean adminComment;//博主回复
    private Integer page;//0普通文章，1关于我页面
    private Boolean notice;//接收邮件提醒
    private Long parentCommentId;//父评论id
    private String qq;//如果评论昵称为QQ号，则将昵称和头像置为QQ昵称和QQ头像，并将此字段置为QQ号备份

    private BlogIdAndTitle blog;//所属的文章
    private List<CommentDO> replyComments = new ArrayList<>();//回复该评论的评论

    public CommentDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getContent() {
        return this.content;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getIp() {
        return this.ip;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public Boolean getAdminComment() {
        return this.adminComment;
    }

    public Integer getPage() {
        return this.page;
    }

    public Boolean getNotice() {
        return this.notice;
    }

    public Long getParentCommentId() {
        return this.parentCommentId;
    }

    public String getQq() {
        return this.qq;
    }

    public BlogIdAndTitle getBlog() {
        return this.blog;
    }

    public List<CommentDO> getReplyComments() {
        return this.replyComments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setNotice(Boolean notice) {
        this.notice = notice;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setBlog(BlogIdAndTitle blog) {
        this.blog = blog;
    }

    public void setReplyComments(List<CommentDO> replyComments) {
        this.replyComments = replyComments;
    }

    @Override
    public String toString() {
        return "CommentDO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", website='" + website + '\'' +
                ", ip='" + ip + '\'' +
                ", published=" + published +
                ", adminComment=" + adminComment +
                ", page=" + page +
                ", notice=" + notice +
                ", parentCommentId=" + parentCommentId +
                ", qq='" + qq + '\'' +
                ", blog=" + blog +
                ", replyComments=" + replyComments +
                '}';
    }
}
