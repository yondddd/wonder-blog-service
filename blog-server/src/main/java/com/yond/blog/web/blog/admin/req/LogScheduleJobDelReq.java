package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class LogScheduleJobDelReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 562029692184334756L;
    
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public LogScheduleJobDelReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogScheduleJobDelReq{" +
                "id=" + id +
                '}';
    }
}
