package com.wonder.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 定时任务日志
 * @Author: Yond
 */
public class LogScheduleJobDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5752837225091432926L;
    private Long id;//日志id
    private Long jobId;//任务id
    private String beanName;//spring bean名称
    private String methodName;//方法名
    private String params;//参数
    private Boolean runStatus;//任务执行结果
    private String error;//异常信息
    private Integer duration;//耗时(单位：毫秒)
    private Date createTime;//创建时间
    private Integer status;

    public LogScheduleJobDO() {
    }

    public Long getId() {
        return id;
    }

    public LogScheduleJobDO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getJobId() {
        return jobId;
    }

    public LogScheduleJobDO setJobId(Long jobId) {
        this.jobId = jobId;
        return this;
    }

    public String getBeanName() {
        return beanName;
    }

    public LogScheduleJobDO setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public LogScheduleJobDO setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getParams() {
        return params;
    }

    public LogScheduleJobDO setParams(String params) {
        this.params = params;
        return this;
    }

    public Boolean getRunStatus() {
        return runStatus;
    }

    public LogScheduleJobDO setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
        return this;
    }

    public String getError() {
        return error;
    }

    public LogScheduleJobDO setError(String error) {
        this.error = error;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public LogScheduleJobDO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LogScheduleJobDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public LogScheduleJobDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "LogScheduleJobDO{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", runStatus=" + runStatus +
                ", error='" + error + '\'' +
                ", duration=" + duration +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
