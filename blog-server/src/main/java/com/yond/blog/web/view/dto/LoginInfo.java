package com.yond.blog.web.view.dto;

/**
 * @Description: 登录账号密码
 * @Author: Yond
 */
public class LoginInfo {
    
    private String username;
    private String password;
    
    public LoginInfo() {
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
        return "LoginInfo(username=" + this.getUsername() + ", password=" + this.getPassword() + ")";
    }
}
