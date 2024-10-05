package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class LogOperationDelReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 9089509026626605992L;
    
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public LogOperationDelReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogOperationDelReq{" +
                "id=" + id +
                '}';
    }
}
