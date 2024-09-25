package com.yond.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yond
 */
public class ScheduleJobStatusReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -3618874557862104403L;

    private Long id;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public ScheduleJobStatusReq setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public ScheduleJobStatusReq setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleJobStatusReq{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
