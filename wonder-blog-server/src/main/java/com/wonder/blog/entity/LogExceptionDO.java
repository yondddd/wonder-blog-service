package com.wonder.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 异常日志
 * @Author: Yond
 */
public class LogExceptionDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1008690861112709577L;

    private Long id;
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String description;//操作描述
    private String error;//异常信息
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Date createTime;//操作时间
    private String userAgent;
    private Integer status;

    public static LogExceptionDO custom() {
        return new LogExceptionDO();
    }

    public LogExceptionDO() {
    }

    public Long getId() {
        return id;
    }

    public LogExceptionDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public LogExceptionDO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public LogExceptionDO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getParam() {
        return param;
    }

    public LogExceptionDO setParam(String param) {
        this.param = param;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogExceptionDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getError() {
        return error;
    }

    public LogExceptionDO setError(String error) {
        this.error = error;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public LogExceptionDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getIpSource() {
        return ipSource;
    }

    public LogExceptionDO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }

    public String getOs() {
        return os;
    }

    public LogExceptionDO setOs(String os) {
        this.os = os;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public LogExceptionDO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LogExceptionDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LogExceptionDO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public LogExceptionDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "LogExceptionDO{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", param='" + param + '\'' +
                ", description='" + description + '\'' +
                ", error='" + error + '\'' +
                ", ip='" + ip + '\'' +
                ", ipSource='" + ipSource + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", createTime=" + createTime +
                ", userAgent='" + userAgent + '\'' +
                ", status=" + status +
                '}';
    }
}
