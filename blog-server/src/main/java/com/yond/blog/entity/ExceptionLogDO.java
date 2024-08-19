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

    public ExceptionLogDO(String uri, String method, String description, String error, String ip, String userAgent) {
        this.uri = uri;
        this.method = method;
        this.description = description;
        this.error = error;
        this.ip = ip;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }

    public ExceptionLogDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUri() {
        return this.uri;
    }

    public String getMethod() {
        return this.method;
    }

    public String getParam() {
        return this.param;
    }

    public String getDescription() {
        return this.description;
    }

    public String getError() {
        return this.error;
    }

    public String getIp() {
        return this.ip;
    }

    public String getIpSource() {
        return this.ipSource;
    }

    public String getOs() {
        return this.os;
    }

    public String getBrowser() {
        return this.browser;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setIpSource(String ipSource) {
        this.ipSource = ipSource;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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
