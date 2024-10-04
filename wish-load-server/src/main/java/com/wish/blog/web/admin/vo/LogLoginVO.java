package com.wish.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogLoginVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 3530973002244993310L;
    
    private Long id;
    private String username;//用户名称
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Boolean loginStatus;//登录状态
    private String description;//操作信息
    private Date createTime;//操作时间
    private String userAgent;
    private Integer status;
    
    public Long getId() {
        return id;
    }
    
    public LogLoginVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getUsername() {
        return username;
    }
    
    public LogLoginVO setUsername(String username) {
        this.username = username;
        return this;
    }
    
    public String getIp() {
        return ip;
    }
    
    public LogLoginVO setIp(String ip) {
        this.ip = ip;
        return this;
    }
    
    public String getIpSource() {
        return ipSource;
    }
    
    public LogLoginVO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }
    
    public String getOs() {
        return os;
    }
    
    public LogLoginVO setOs(String os) {
        this.os = os;
        return this;
    }
    
    public String getBrowser() {
        return browser;
    }
    
    public LogLoginVO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }
    
    public Boolean getLoginStatus() {
        return loginStatus;
    }
    
    public LogLoginVO setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LogLoginVO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public LogLoginVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public LogLoginVO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public LogLoginVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogLoginVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", ipSource='" + ipSource + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", loginStatus=" + loginStatus +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", userAgent='" + userAgent + '\'' +
                ", status=" + status +
                '}';
    }
}
