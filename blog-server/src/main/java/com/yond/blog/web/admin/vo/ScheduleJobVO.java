package com.yond.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yond
 */
public class ScheduleJobVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 487171220122897673L;
    
    private Long id;
    private String beanName;
    private String methodName;
    private String params;
    private String cron;
    private Boolean runStatus;
    private Integer status;
    private String remark;
    private Date createTime;
    
    public Long getId() {
        return id;
    }
    
    public ScheduleJobVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getBeanName() {
        return beanName;
    }
    
    public ScheduleJobVO setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public ScheduleJobVO setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }
    
    public String getParams() {
        return params;
    }
    
    public ScheduleJobVO setParams(String params) {
        this.params = params;
        return this;
    }
    
    public String getCron() {
        return cron;
    }
    
    public ScheduleJobVO setCron(String cron) {
        this.cron = cron;
        return this;
    }
    
    public Boolean getRunStatus() {
        return runStatus;
    }
    
    public ScheduleJobVO setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
        return this;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public ScheduleJobVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public ScheduleJobVO setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public ScheduleJobVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    @Override
    public String toString() {
        return "ScheduleJobVO{" +
                "id=" + id +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cron='" + cron + '\'' +
                ", runStatus=" + runStatus +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
