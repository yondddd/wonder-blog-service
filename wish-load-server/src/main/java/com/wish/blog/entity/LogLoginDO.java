package com.wish.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 登录日志
 * @Author: Yond
 */
public class LogLoginDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6801601296035820131L;

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

    public LogLoginDO(String username, String ip, boolean loginStatus, String description, String userAgent) {
        this.username = username;
        this.ip = ip;
        this.loginStatus = loginStatus;
        this.description = description;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }

    public LogLoginDO() {
    }

    public Long getId() {
        return id;
    }

    public LogLoginDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LogLoginDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public LogLoginDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getIpSource() {
        return ipSource;
    }

    public LogLoginDO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }

    public String getOs() {
        return os;
    }

    public LogLoginDO setOs(String os) {
        this.os = os;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public LogLoginDO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public LogLoginDO setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogLoginDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LogLoginDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LogLoginDO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public LogLoginDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "LogLoginDO{" +
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