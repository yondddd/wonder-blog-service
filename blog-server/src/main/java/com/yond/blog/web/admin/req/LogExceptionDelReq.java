package com.yond.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class LogExceptionDelReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -1252253858272072845L;
    
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public LogExceptionDelReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogExceptionDelReq{" +
                "id=" + id +
                '}';
    }
}
