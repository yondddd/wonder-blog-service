package com.wonder.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 定时任务
 * @Author: Yond
 */
public class ScheduleJobDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1842150725534943656L;
    
    private Long id;//任务id
    private String beanName;//spring bean名称
    private String methodName;//方法名
    private String params;//参数
    private String cron;//cron表达式
    private Boolean runStatus;//任务状态
    private Integer status;
    private String remark;//备注
    private Date createTime;//创建时间
    
    public static ScheduleJobDO custom() {
        return new ScheduleJobDO();
    }
    
    public ScheduleJobDO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public ScheduleJobDO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getBeanName() {
        return beanName;
    }
    
    public ScheduleJobDO setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public ScheduleJobDO setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }
    
    public String getParams() {
        return params;
    }
    
    public ScheduleJobDO setParams(String params) {
        this.params = params;
        return this;
    }
    
    public String getCron() {
        return cron;
    }
    
    public ScheduleJobDO setCron(String cron) {
        this.cron = cron;
        return this;
    }
    
    public Boolean getRunStatus() {
        return runStatus;
    }
    
    public ScheduleJobDO setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
        return this;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public ScheduleJobDO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public ScheduleJobDO setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public ScheduleJobDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    
    @Override
    public String toString() {
        return "ScheduleJobDO{" +
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
