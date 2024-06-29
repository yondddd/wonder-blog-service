package com.yond.common.req;

import java.io.Serializable;

/**
 * @author yond
 * @date 2024/4/6
 * @description page rep
 */
public class PageReq implements Serializable {

    private static final long serialVersionUID = 2760764853696416726L;

    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
