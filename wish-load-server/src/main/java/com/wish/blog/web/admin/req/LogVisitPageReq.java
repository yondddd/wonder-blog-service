package com.wish.blog.web.admin.req;

import com.wish.common.req.PageReq;

import java.io.Serial;
import java.util.Date;

/**
 * @Author: Yond
 */
public class LogVisitPageReq extends PageReq {
    
    @Serial
    private static final long serialVersionUID = -8608398607341105083L;
    
    private String uuid;
    private Date startDate;
    private Date endDate;
    
    public String getUuid() {
        return uuid;
    }
    
    public LogVisitPageReq setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public LogVisitPageReq setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public LogVisitPageReq setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogVisitPageReq{" +
                "uuid='" + uuid + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
