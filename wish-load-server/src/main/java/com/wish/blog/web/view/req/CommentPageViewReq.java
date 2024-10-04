package com.wish.blog.web.view.req;

import com.wish.common.enums.CommentPageEnum;
import com.wish.common.req.PageReq;

import java.io.Serial;

/**
 * @Description:
 * @Author: Yond
 */
public class CommentPageViewReq extends PageReq {
    
    @Serial
    private static final long serialVersionUID = 557219188118481112L;
    
    /**
     * {@link CommentPageEnum}
     */
    private Integer page;
    private Long blogId;
    
    public Integer getPage() {
        return page;
    }
    
    public CommentPageViewReq setPage(Integer page) {
        this.page = page;
        return this;
    }
    
    public Long getBlogId() {
        return blogId;
    }
    
    public CommentPageViewReq setBlogId(Long blogId) {
        this.blogId = blogId;
        return this;
    }
    
    @Override
    public String toString() {
        return "CommentPageViewReq{" +
                "page=" + page +
                ", blogId=" + blogId +
                '}';
    }
    
}
