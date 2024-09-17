package com.yond.blog.web.blog.view.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class FriendClickReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -2952888245646871337L;
    
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public FriendClickReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendClickReq{" +
                "id=" + id +
                '}';
    }
}
