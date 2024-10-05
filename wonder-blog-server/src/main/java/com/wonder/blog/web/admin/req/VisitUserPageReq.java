package com.wonder.blog.web.admin.req;

import com.wonder.common.req.PageReq;

import java.io.Serial;
import java.util.Date;

/**
 * @author Yond
 */
public class VisitUserPageReq extends PageReq {

    @Serial
    private static final long serialVersionUID = 864220720503902334L;

    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public VisitUserPageReq setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public VisitUserPageReq setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    @Override
    public String toString() {
        return "VisitUserPageReq{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
