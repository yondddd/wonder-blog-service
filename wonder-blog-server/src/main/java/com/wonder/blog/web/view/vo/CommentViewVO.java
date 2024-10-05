package com.wonder.blog.web.view.vo;

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
    private Long parentCommentId;//父评论id
    private String parentCommentNickname;//父评论昵称

    private List<CommentViewVO> replyComments = new ArrayList<>();//回复该评论的评论

    public CommentViewVO() {
    }

    public Long getId() {
        return id;
    }

    public CommentViewVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public CommentViewVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentViewVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public CommentViewVO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CommentViewVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CommentViewVO setWebsite(String website) {
        this.website = website;
        return this;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public CommentViewVO setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
        return this;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public CommentViewVO setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
        return this;
    }

    public String getParentCommentNickname() {
        return parentCommentNickname;
    }

    public CommentViewVO setParentCommentNickname(String parentCommentNickname) {
        this.parentCommentNickname = parentCommentNickname;
        return this;
    }

    public List<CommentViewVO> getReplyComments() {
        return replyComments;
    }

    public CommentViewVO setReplyComments(List<CommentViewVO> replyComments) {
        this.replyComments = replyComments;
        return this;
    }

    @Override
    public String toString() {
        return "CommentViewVO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", website='" + website + '\'' +
                ", adminComment=" + adminComment +
                ", parentCommentId=" + parentCommentId +
                ", parentCommentNickname='" + parentCommentNickname + '\'' +
                ", replyComments=" + replyComments +
                '}';
    }
}
