package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class AboutVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 7849965494576292034L;
    
    private String content;
    private String commentEnabled;
    
    public String getContent() {
        return content;
    }
    
    public AboutVO setContent(String content) {
        this.content = content;
        return this;
    }
    
    public String getCommentEnabled() {
        return commentEnabled;
    }
    
    public AboutVO setCommentEnabled(String commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }
    
    @Override
    public String toString() {
        return "AboutVO{" +
                "content='" + content + '\'' +
                ", commentEnabled='" + commentEnabled + '\'' +
                '}';
    }
}
