package com.yond.blog.web.admin.req;

import com.yond.common.req.PageReq;

import java.io.Serial;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogOperationPageReq extends PageReq {
    
    @Serial
    private static final long serialVersionUID = -8254395778016714916L;
    
    private Date startDate;
    private Date endDate;
    
    public Date getStartDate() {
        return startDate;
    }
    
    public LogOperationPageReq setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public LogOperationPageReq setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogOperationPageReq{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
