package com.yond.blog.web.blog.view.req;

import com.yond.common.enums.CommentPageEnum;
import com.yond.common.req.PageReq;

import java.io.Serial;

/**
 * @Description:
 * @Author: WangJieLong
 * @Date: 2024-09-13
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
