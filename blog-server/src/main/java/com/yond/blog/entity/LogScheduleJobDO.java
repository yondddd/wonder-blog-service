package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 定时任务日志
 * @Author: Yond
 * @Date: 2020-11-01
 */
public class LogScheduleJobDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5752837225091432926L;
    private Long id;//日志id
    private Long jobId;//任务id
    private String beanName;//spring bean名称
    private String methodName;//方法名
    private String params;//参数
    private Boolean status;//任务执行结果
    private String error;//异常信息
    private Integer times;//耗时(单位：毫秒)
    private Date createTime;//创建时间

    public LogScheduleJobDO() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getJobId() {
        return this.jobId;
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

    public Boolean getStatus() {
        return this.status;
    }

    public String getError() {
        return this.error;
    }

    public Integer getTimes() {
        return this.times;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
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

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScheduleJobLogDO{" +
                "logId=" + id +
                ", jobId=" + jobId +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", times=" + times +
                ", createTime=" + createTime +
                '}';
    }
}
