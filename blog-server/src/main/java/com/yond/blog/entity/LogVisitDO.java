package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 访问日志
 * @Author: Naccl
 * @Date: 2020-12-04
 */
public class LogVisitDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3567996804412972125L;
    private Long id;
    private String uuid;//访客标识码
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String behavior;//访问行为
    private String content;//访问内容
    private String remark;//备注
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Integer times;//请求耗时（毫秒）
    private Date createTime;//访问时间
    private String userAgent;


    public static LogVisitDO custom() {
        return new LogVisitDO();
    }

    public LogVisitDO() {
    }

    public String getIpSource() {
        return ipSource;
    }

    public LogVisitDO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }

    public Long getId() {
        return id;
    }

    public LogVisitDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public LogVisitDO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public LogVisitDO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public LogVisitDO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getParam() {
        return param;
    }

    public LogVisitDO setParam(String param) {
        this.param = param;
        return this;
    }

    public String getBehavior() {
        return behavior;
    }

    public LogVisitDO setBehavior(String behavior) {
        this.behavior = behavior;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LogVisitDO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public LogVisitDO setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public LogVisitDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getOs() {
        return os;
    }

    public LogVisitDO setOs(String os) {
        this.os = os;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public LogVisitDO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public Integer getTimes() {
        return times;
    }

    public LogVisitDO setTimes(Integer times) {
        this.times = times;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LogVisitDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LogVisitDO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public String toString() {
        return "VisitLogDO{" +
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
                ", times=" + times +
                ", createTime=" + createTime +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
