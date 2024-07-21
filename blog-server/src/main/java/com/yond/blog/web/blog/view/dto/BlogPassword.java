package com.yond.blog.web.blog.view.dto;

/**
 * @Description: 受保护文章密码DTO
 * @Author: Naccl
 * @Date: 2020-09-05
 */
public class BlogPassword {
    private Long blogId;
    private String password;

    public BlogPassword() {
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
