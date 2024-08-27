package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 异常日志
 * @Author: Naccl
 * @Date: 2020-12-03
 */
public class ExceptionLogDO implements Serializable {

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

    public static ExceptionLogDO custom(){
        return new ExceptionLogDO();
    }
    
    public ExceptionLogDO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public ExceptionLogDO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getUri() {
        return uri;
    }
    
    public ExceptionLogDO setUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    public String getMethod() {
        return method;
    }
    
    public ExceptionLogDO setMethod(String method) {
        this.method = method;
        return this;
    }
    
    public String getParam() {
        return param;
    }
    
    public ExceptionLogDO setParam(String param) {
        this.param = param;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public ExceptionLogDO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getError() {
        return error;
    }
    
    public ExceptionLogDO setError(String error) {
        this.error = error;
        return this;
    }
    
    public String getIp() {
        return ip;
    }
    
    public ExceptionLogDO setIp(String ip) {
        this.ip = ip;
        return this;
    }
    
    public String getIpSource() {
        return ipSource;
    }
    
    public ExceptionLogDO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }
    
    public String getOs() {
        return os;
    }
    
    public ExceptionLogDO setOs(String os) {
        this.os = os;
        return this;
    }
    
    public String getBrowser() {
        return browser;
    }
    
    public ExceptionLogDO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public ExceptionLogDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public ExceptionLogDO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }
    
    @Override
    public String toString() {
        return "ExceptionLogDO{" +
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
                '}';
    }
}
