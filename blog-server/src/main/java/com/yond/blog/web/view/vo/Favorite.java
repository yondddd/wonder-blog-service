package com.yond.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 自定义爱好
 * @Author: Yond
 */
public class Favorite implements Serializable {
    @Serial
    private static final long serialVersionUID = 1003846806944482693L;
    private String title;
    private String content;
    
    public Favorite() {
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String toString() {
        return "Favorite(title=" + this.getTitle() + ", content=" + this.getContent() + ")";
    }
}
