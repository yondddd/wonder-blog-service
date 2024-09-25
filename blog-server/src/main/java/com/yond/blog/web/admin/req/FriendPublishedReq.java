package com.yond.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class FriendPublishedReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -7006776541943220747L;
    
    private Long id;
    private Boolean published;
    
    public Long getId() {
        return id;
    }
    
    public FriendPublishedReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public FriendPublishedReq setPublished(Boolean published) {
        this.published = published;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendPublishedReq{" +
                "id=" + id +
                ", published=" + published +
                '}';
    }
    
}
