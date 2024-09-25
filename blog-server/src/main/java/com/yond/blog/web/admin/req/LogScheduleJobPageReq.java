package com.yond.blog.web.admin.req;

import com.yond.common.req.PageReq;

import java.io.Serial;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogScheduleJobPageReq extends PageReq {
    
    @Serial
    private static final long serialVersionUID = 4394723920932361309L;
    
    private Date startDate;
    private Date endDate;
    
    public Date getStartDate() {
        return startDate;
    }
    
    public LogScheduleJobPageReq setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public LogScheduleJobPageReq setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogScheduleJobPageReq{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
