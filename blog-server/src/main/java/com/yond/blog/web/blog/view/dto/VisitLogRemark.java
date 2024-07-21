package com.yond.blog.web.blog.view.dto;

/**
 * 访问日志备注
 *
 * @author: Naccl
 * @date: 2022-01-08
 */
public class VisitLogRemark {
    /**
     * 访问内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    public VisitLogRemark(String content, String remark) {
        this.content = content;
        this.remark = remark;
    }

    public VisitLogRemark() {
    }

    public String getContent() {
        return this.content;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return "VisitLogRemark(content=" + this.getContent() + ", remark=" + this.getRemark() + ")";
    }
}
