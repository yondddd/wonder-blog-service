package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class LogLoginDelReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 3870998003023973206L;
    
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public LogLoginDelReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "LogLoginDelReq{" +
                "id=" + id +
                '}';
    }
}
