package com.yond.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 */
public class FriendConfigVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7871830209305468014L;

    private String content;
    private Boolean commentEnabled;

    public String getContent() {
        return content;
    }

    public FriendConfigVO setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getCommentEnabled() {
        return commentEnabled;
    }

    public FriendConfigVO setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }

    @Override
    public String toString() {
        return "FriendConfigVO{" +
                "content='" + content + '\'' +
                ", commentEnabled=" + commentEnabled +
                '}';
    }
}
