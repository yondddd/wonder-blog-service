package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 访问日志
 * @Author: Naccl
 * @Date: 2020-12-04
 */
public class VisitLogDO implements Serializable {
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

    public VisitLogDO(String uuid, String uri, String method, String behavior, String content, String remark, String ip, Integer times, String userAgent) {
        this.uuid = uuid;
        this.uri = uri;
        this.method = method;
        this.behavior = behavior;
        this.content = content;
        this.remark = remark;
        this.ip = ip;
        this.times = times;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }

    public VisitLogDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUuid() {
        return this.uuid;
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

    public String getBehavior() {
        return this.behavior;
    }

    public String getContent() {
        return this.content;
    }

    public String getRemark() {
        return this.remark;
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

    public Integer getTimes() {
        return this.times;
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

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public void setTimes(Integer times) {
        this.times = times;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String toString() {
        return "VisitLogDO(id=" + this.getId() + ", uuid=" + this.getUuid() + ", uri=" + this.getUri() + ", method=" + this.getMethod() + ", param=" + this.getParam() + ", behavior=" + this.getBehavior() + ", content=" + this.getContent() + ", remark=" + this.getRemark() + ", ip=" + this.getIp() + ", ipSource=" + this.getIpSource() + ", os=" + this.getOs() + ", browser=" + this.getBrowser() + ", times=" + this.getTimes() + ", createTime=" + this.getCreateTime() + ", userAgent=" + this.getUserAgent() + ")";
    }
}
