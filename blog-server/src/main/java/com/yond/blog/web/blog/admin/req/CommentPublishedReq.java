package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 更新评论公开状态
 * @Author: WangJieLong
 * @Date: 2024-09-13
 */
public class CommentPublishedReq implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -8246467815566956385L;
    
    private Long id;
    private Boolean published;
    
    public Long getId() {
        return id;
    }
    
    public CommentPublishedReq setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public CommentPublishedReq setPublished(Boolean published) {
        this.published = published;
        return this;
    }
    
    @Override
    public String toString() {
        String sb = "CommentPublishedReq{" + "id=" + id +
                ", published=" + published +
                '}';
        return sb;
    }
}
