package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 * @Date: 2024-09-13
 */
public class CommentNoticeReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -884477097932070725L;

    private Long id;
    private Boolean notice;

    public Long getId() {
        return id;
    }

    public CommentNoticeReq setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getNotice() {
        return notice;
    }

    public CommentNoticeReq setNotice(Boolean notice) {
        this.notice = notice;
        return this;
    }

    @Override
    public String toString() {
        String sb = "CommentNoticeReq{" + "id=" + id +
                ", notice=" + notice +
                '}';
        return sb;
    }
}
