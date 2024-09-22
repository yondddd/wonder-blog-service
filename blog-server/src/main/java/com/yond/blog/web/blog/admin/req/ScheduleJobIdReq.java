package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yond
 */
public class ScheduleJobIdReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -7857495591291627810L;

    private Long id;

    public Long getId() {
        return id;
    }

    public ScheduleJobIdReq setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleJobDelReq{" +
                "id=" + id +
                '}';
    }
}
