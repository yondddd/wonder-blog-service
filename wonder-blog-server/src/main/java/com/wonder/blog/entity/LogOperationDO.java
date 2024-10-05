package com.wonder.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 操作日志
 * @Author: Yond
 */
public class LogOperationDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4118817003939716005L;

    private Long id;
    private String username;//操作者用户名
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String description;//操作描述
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Integer duration;//请求耗时（毫秒）
    private Date createTime;//操作时间
    private String userAgent;
    private Integer status;

    public static LogOperationDO custom() {
        return new LogOperationDO();
    }

    public LogOperationDO() {
    }

    public Long getId() {
        return id;
    }

    public LogOperationDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LogOperationDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public LogOperationDO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public LogOperationDO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getParam() {
        return param;
    }

    public LogOperationDO setParam(String param) {
        this.param = param;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogOperationDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public LogOperationDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getIpSource() {
        return ipSource;
    }

    public LogOperationDO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }

    public String getOs() {
        return os;
    }

    public LogOperationDO setOs(String os) {
        this.os = os;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public LogOperationDO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public LogOperationDO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LogOperationDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LogOperationDO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public LogOperationDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "LogOperationDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", param='" + param + '\'' +
                ", description='" + description + '\'' +
                ", ip='" + ip + '\'' +
                ", ipSource='" + ipSource + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", duration=" + duration +
                ", createTime=" + createTime +
                ", userAgent='" + userAgent + '\'' +
                ", status=" + status +
                '}';
    }
}
