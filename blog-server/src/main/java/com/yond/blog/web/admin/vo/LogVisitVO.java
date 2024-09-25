package com.yond.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogVisitVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -8904823376878506501L;
    
    private Long id;
    private String uuid;
    private String uri;
    private String method;
    private String param;
    private String behavior;
    private String content;
    private String remark;
    private String ip;
    private String ipSource;
    private String os;
    private String browser;
    private Integer duration;
    private Date createTime;
    private String userAgent;
    private Integer status;
    
    public Long getId() {
        return id;
    }
    
    public LogVisitVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public LogVisitVO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
    
    public String getUri() {
        return uri;
    }
    
    public LogVisitVO setUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    public String getMethod() {
        return method;
    }
    
    public LogVisitVO setMethod(String method) {
        this.method = method;
        return this;
    }
    
    public String getParam() {
        return param;
    }
    
    public LogVisitVO setParam(String param) {
        this.param = param;
        return this;
    }
    
    public String getBehavior() {
        return behavior;
    }
    
    public LogVisitVO setBehavior(String behavior) {
        this.behavior = behavior;
        return this;
    }
    
    public String getContent() {
        return content;
    }
    
    public LogVisitVO setContent(String content) {
        this.content = content;
        return this;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public LogVisitVO setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    
    public String getIp() {
        return ip;
    }
    
    public LogVisitVO setIp(String ip) {
        this.ip = ip;
        return this;
    }
    
    public String getIpSource() {
        return ipSource;
    }
    
    public LogVisitVO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }
    
    public String getOs() {
        return os;
    }
    
    public LogVisitVO setOs(String os) {
        this.os = os;
        return this;
    }
    
    public String getBrowser() {
        return browser;
    }
    
    public LogVisitVO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public LogVisitVO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public LogVisitVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public LogVisitVO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public LogVisitVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogVisitVO{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", param='" + param + '\'' +
                ", behavior='" + behavior + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
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
