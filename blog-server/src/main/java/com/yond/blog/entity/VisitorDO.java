package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 访客记录
 * @Author: Naccl
 * @Date: 2021-01-31
 */
public class VisitorDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6715140594847056682L;
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

    public VisitorDO(String uuid, String ip, String userAgent) {
        this.uuid = uuid;
        this.ip = ip;
        Date date = new Date();
        this.createTime = date;
        this.lastTime = date;
        this.pv = 0;
        this.userAgent = userAgent;
    }

    public VisitorDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUuid() {
        return this.uuid;
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

    public Date getLastTime() {
        return this.lastTime;
    }

    public Integer getPv() {
        return this.pv;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String toString() {
        return "VisitorDO(id=" + this.getId() + ", uuid=" + this.getUuid() + ", ip=" + this.getIp() + ", ipSource=" + this.getIpSource() + ", os=" + this.getOs() + ", browser=" + this.getBrowser() + ", createTime=" + this.getCreateTime() + ", lastTime=" + this.getLastTime() + ", pv=" + this.getPv() + ", userAgent=" + this.getUserAgent() + ")";
    }
}
