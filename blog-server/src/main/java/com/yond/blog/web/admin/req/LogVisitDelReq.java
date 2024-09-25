package com.yond.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class LogVisitDelReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -7046165704400849639L;
    
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public LogVisitDelReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogVisitDelReq{" +
                "id=" + id +
                '}';
    }
    
}
