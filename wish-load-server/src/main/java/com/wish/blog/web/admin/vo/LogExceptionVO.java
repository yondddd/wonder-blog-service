package com.wish.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogExceptionVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -313110177918686761L;
    
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
    
    public Long getId() {
        return id;
    }
    
    public LogExceptionVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getUri() {
        return uri;
    }
    
    public LogExceptionVO setUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    public String getMethod() {
        return method;
    }
    
    public LogExceptionVO setMethod(String method) {
        this.method = method;
        return this;
    }
    
    public String getParam() {
        return param;
    }
    
    public LogExceptionVO setParam(String param) {
        this.param = param;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LogExceptionVO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getError() {
        return error;
    }
    
    public LogExceptionVO setError(String error) {
        this.error = error;
        return this;
    }
    
    public String getIp() {
        return ip;
    }
    
    public LogExceptionVO setIp(String ip) {
        this.ip = ip;
        return this;
    }
    
    public String getIpSource() {
        return ipSource;
    }
    
    public LogExceptionVO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }
    
    public String getOs() {
        return os;
    }
    
    public LogExceptionVO setOs(String os) {
        this.os = os;
        return this;
    }
    
    public String getBrowser() {
        return browser;
    }
    
    public LogExceptionVO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public LogExceptionVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public LogExceptionVO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public LogExceptionVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogExceptionVO{" +
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
