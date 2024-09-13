package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: WangJieLong
 * @Date: 2024-09-13
 */
public class CommentDelReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -2179029703758637921L;
    
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public CommentDelReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "CommentDelReq{" +
                "id=" + id +
                '}';
    }
}
