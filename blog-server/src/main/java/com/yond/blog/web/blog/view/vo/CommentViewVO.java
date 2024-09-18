package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 页面评论
 * @Author: Yond
 */
public class CommentViewVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2156320836527320699L;
    private Long id;
    private String nickname;//昵称
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    private Date createTime;//评论时间
    private String website;//个人网站
    private Boolean adminComment;//博主回复
    private String parentCommentId;//父评论id
    private String parentCommentNickname;//父评论昵称

    private List<CommentViewVO> replyComments = new ArrayList<>();//回复该评论的评论

    public CommentViewVO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
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

    public Boolean getAdminComment() {
        return this.adminComment;
    }

    public String getParentCommentId() {
        return this.parentCommentId;
    }

    public String getParentCommentNickname() {
        return this.parentCommentNickname;
    }

    public List<CommentViewVO> getReplyComments() {
        return this.replyComments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public void setParentCommentNickname(String parentCommentNickname) {
        this.parentCommentNickname = parentCommentNickname;
    }

    public void setReplyComments(List<CommentViewVO> replyComments) {
        this.replyComments = replyComments;
    }

    public String toString() {
        return "PageComment(id=" + this.getId() + ", nickname=" + this.getNickname() + ", content=" + this.getContent() + ", avatar=" + this.getAvatar() + ", createTime=" + this.getCreateTime() + ", website=" + this.getWebsite() + ", adminComment=" + this.getAdminComment() + ", parentCommentId=" + this.getParentCommentId() + ", parentCommentNickname=" + this.getParentCommentNickname() + ", replyComments=" + this.getReplyComments() + ")";
    }
}
