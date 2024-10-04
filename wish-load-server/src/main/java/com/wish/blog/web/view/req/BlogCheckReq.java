package com.wish.blog.web.view.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 受保护文章密码DTO
 * @Author: Yond
 */
public class BlogCheckReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4737643043185798231L;
    
    private Long blogId;
    private String password;
    
    public BlogCheckReq() {
    }
    
    public Long getBlogId() {
        return this.blogId;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
        return "BlogPassword(blogId=" + this.getBlogId() + ", password=" + this.getPassword() + ")";
    }
}
