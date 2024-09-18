package com.yond.blog.web.blog.admin.vo;

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
    private Integer times;
    private Date createTime;
    private String userAgent;
    private Integer status;
}
