package com.yond.blog.entity;

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
    
    public Boolean getRunStatus() {
        return this.runStatus;
    }
    
    public String getError() {
        return this.error;
    }
    
    public Integer getDuration() {
        return this.duration;
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
    
    public void setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
