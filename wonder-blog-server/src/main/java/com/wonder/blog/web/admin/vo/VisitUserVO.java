package com.wonder.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yond
 * @date 9/19/2024
 * @description
 */
public class VisitUserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6784046558073308386L;

    private Long id;
    private String uuid;//访客标识码
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Date createTime;//首次访问时间
    private Date lastTime;//最后访问时间
    private Integer pv;//访问页数统计
    private String userAgent;

    public Long getId() {
        return id;
    }

    public VisitUserVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public VisitUserVO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public VisitUserVO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getIpSource() {
        return ipSource;
    }

    public VisitUserVO setIpSource(String ipSource) {
        this.ipSource = ipSource;
        return this;
    }

    public String getOs() {
        return os;
    }

    public VisitUserVO setOs(String os) {
        this.os = os;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public VisitUserVO setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public VisitUserVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public VisitUserVO setLastTime(Date lastTime) {
        this.lastTime = lastTime;
        return this;
    }

    public Integer getPv() {
        return pv;
    }

    public VisitUserVO setPv(Integer pv) {
        this.pv = pv;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public VisitUserVO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public String toString() {
        return "VisitUserVO{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", ip='" + ip + '\'' +
                ", ipSource='" + ipSource + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", createTime=" + createTime +
                ", lastTime=" + lastTime +
                ", pv=" + pv +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
