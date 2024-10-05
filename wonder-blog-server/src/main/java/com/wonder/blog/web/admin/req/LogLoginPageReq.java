package com.wonder.blog.web.admin.req;

import com.wonder.common.req.PageReq;

import java.io.Serial;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogLoginPageReq extends PageReq {
    
    @Serial
    private static final long serialVersionUID = -1542942727726675591L;
    
    private Date startDate;
    private Date endDate;
    
    public Date getStartDate() {
        return startDate;
    }
    
    public LogLoginPageReq setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public LogLoginPageReq setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogLoginPageReq{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
