package com.wish.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class FriendUpdateConfigReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -7092470706713356655L;
    
    private String content;
    private Boolean commentEnabled;
    
    public String getContent() {
        return content;
    }
    
    public FriendUpdateConfigReq setContent(String content) {
        this.content = content;
        return this;
    }
    
    public Boolean getCommentEnabled() {
        return commentEnabled;
    }
    
    public FriendUpdateConfigReq setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendUpdateConfigReq{" +
                "content='" + content + '\'' +
                ", commentEnabled=" + commentEnabled +
                '}';
    }
    
}
