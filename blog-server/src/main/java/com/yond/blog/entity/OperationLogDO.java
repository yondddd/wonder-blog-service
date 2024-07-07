package com.yond.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 操作日志
 * @Author: Naccl
 * @Date: 2020-11-30
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OperationLogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4118817003939716005L;

    private Long id;
    private String username;//操作者用户名
    private String uri;//请求接口
    private String method;//请求方式
    private String param;//请求参数
    private String description;//操作描述
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Integer times;//请求耗时（毫秒）
    private Date createTime;//操作时间
    private String userAgent;

    public OperationLogDO(String username, String uri, String method, String description, String ip, Integer times, String userAgent) {
        this.username = username;
        this.uri = uri;
        this.method = method;
        this.description = description;
        this.ip = ip;
        this.times = times;
        this.createTime = new Date();
        this.userAgent = userAgent;
    }
}
