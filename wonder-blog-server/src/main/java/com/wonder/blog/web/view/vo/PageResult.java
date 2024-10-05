package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页结果
 * @Author: Yond
 */
public class PageResult<T> implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 6491477600133621506L;
    
    private Integer totalPage;//总页数
    private List<T> list;//数据
    
    public PageResult(Integer totalPage, List<T> list) {
        this.totalPage = totalPage;
        this.list = list;
    }
    
    public PageResult() {
    }
    
    public Integer getTotalPage() {
        return this.totalPage;
    }
    
    public List<T> getList() {
        return this.list;
    }
    
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    
    public void setList(List<T> list) {
        this.list = list;
    }
    
    public String toString() {
        return "PageResult(totalPage=" + this.getTotalPage() + ", list=" + this.getList() + ")";
    }
}
