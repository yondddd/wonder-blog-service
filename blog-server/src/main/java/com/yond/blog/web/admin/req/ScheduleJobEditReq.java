package com.yond.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yond
 */
public class ScheduleJobEditReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 8000903117318918536L;

    private Long id;
    private String beanName;
    private String methodName;
    private String params;
    private String cron;
    private String remark;

    public Long getId() {
        return id;
    }

    public ScheduleJobEditReq setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBeanName() {
        return beanName;
    }

    public ScheduleJobEditReq setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public ScheduleJobEditReq setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getParams() {
        return params;
    }

    public ScheduleJobEditReq setParams(String params) {
        this.params = params;
        return this;
    }

    public String getCron() {
        return cron;
    }

    public ScheduleJobEditReq setCron(String cron) {
        this.cron = cron;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public ScheduleJobEditReq setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleJobEditReq{" +
                "id=" + id +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cron='" + cron + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
