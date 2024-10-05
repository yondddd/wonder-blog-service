package com.wonder.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogScheduleJobVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4363027545322418651L;
    
    private Long id;
    private Long jobId;
    private String beanName;
    private String methodName;
    private String params;
    private Boolean runStatus;
    private String error;
    private Integer duration;
    private Date createTime;
    private Integer status;
    
    public Long getId() {
        return id;
    }
    
    public LogScheduleJobVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Long getJobId() {
        return jobId;
    }
    
    public LogScheduleJobVO setJobId(Long jobId) {
        this.jobId = jobId;
        return this;
    }
    
    public String getBeanName() {
        return beanName;
    }
    
    public LogScheduleJobVO setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public LogScheduleJobVO setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }
    
    public String getParams() {
        return params;
    }
    
    public LogScheduleJobVO setParams(String params) {
        this.params = params;
        return this;
    }
    
    public Boolean getRunStatus() {
        return runStatus;
    }
    
    public LogScheduleJobVO setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
        return this;
    }
    
    public String getError() {
        return error;
    }
    
    public LogScheduleJobVO setError(String error) {
        this.error = error;
        return this;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public LogScheduleJobVO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public LogScheduleJobVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public LogScheduleJobVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogScheduleJobVO{" +
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
