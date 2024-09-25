package com.yond.blog.web.admin.dto;

/**
 * @Author: Yond
 */
public class FriendConfigDTO {
    
    private String content;
    private String htmlContent;
    private Boolean commentEnabled;
    
    public String getContent() {
        return content;
    }
    
    public FriendConfigDTO setContent(String content) {
        this.content = content;
        return this;
    }
    
    public String getHtmlContent() {
        return htmlContent;
    }
    
    public FriendConfigDTO setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
        return this;
    }
    
    public Boolean getCommentEnabled() {
        return commentEnabled;
    }
    
    public FriendConfigDTO setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendConfigDTO{" +
                "content='" + content + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", commentEnabled=" + commentEnabled +
                '}';
    }
}
