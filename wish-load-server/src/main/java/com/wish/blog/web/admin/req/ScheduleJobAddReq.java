package com.wish.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class ScheduleJobAddReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -526171277334531828L;

    private String beanName;
    private String methodName;
    private String params;
    private String cron;
    private String remark;

    public String getBeanName() {
        return beanName;
    }

    public ScheduleJobAddReq setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public ScheduleJobAddReq setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getParams() {
        return params;
    }

    public ScheduleJobAddReq setParams(String params) {
        this.params = params;
        return this;
    }

    public String getCron() {
        return cron;
    }

    public ScheduleJobAddReq setCron(String cron) {
        this.cron = cron;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public ScheduleJobAddReq setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleJobAddReq{" +
                "beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cron='" + cron + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
