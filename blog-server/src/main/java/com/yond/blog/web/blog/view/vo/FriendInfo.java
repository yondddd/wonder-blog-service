package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 友链页面信息
 * @Author: Naccl
 * @Date: 2020-09-09
 */
public class FriendInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -3609256529138860536L;
    private String content;
    private Boolean commentEnabled;

    public FriendInfo() {
    }

    public String getContent() {
        return this.content;
    }

    public Boolean getCommentEnabled() {
        return this.commentEnabled;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
    }

    public String toString() {
        return "FriendInfo(content=" + this.getContent() + ", commentEnabled=" + this.getCommentEnabled() + ")";
    }
}
