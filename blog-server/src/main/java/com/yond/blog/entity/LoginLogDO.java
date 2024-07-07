package com.yond.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 登录日志
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginLogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6801601296035820131L;

    private Long id;
    private String username;//用户名称
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Boolean status;//登录状态
    private String description;//操作信息
    private Date createTime;//操作时间
    private String userAgent;

    public LoginLogDO(String username, String ip, boolean status, String description, String userAgent) {
        this.username = username;
        this.ip = ip;
        this.status = status;
        this.description = description;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }
}