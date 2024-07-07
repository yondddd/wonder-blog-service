package com.yond.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 访客记录
 * @Author: Naccl
 * @Date: 2021-01-31
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
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
}
