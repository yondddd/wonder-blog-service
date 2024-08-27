package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 定时任务
 * @Author: Naccl
 * @Date: 2020-11-01
 */
public class ScheduleJobDO implements Serializable {

    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY"; //任务调度参数key
    @Serial
    private static final long serialVersionUID = 1842150725534943656L;

    private Long id;//任务id
    private String beanName;//spring bean名称
    private String methodName;//方法名
    private String params;//参数
    private String cron;//cron表达式
    private Boolean status;//任务状态
    private String remark;//备注
    private Date createTime;//创建时间

    public ScheduleJobDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getParams() {
        return this.params;
    }

    public String getCron() {
        return this.cron;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public String getRemark() {
        return this.remark;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScheduleJobDO{" +
                "id=" + id +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cron='" + cron + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
