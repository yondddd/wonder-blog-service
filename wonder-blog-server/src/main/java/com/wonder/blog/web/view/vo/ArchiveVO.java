package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Yond
 */
public class ArchiveVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5401806367452620008L;
    
    private Integer total;
    private List<ArchiveGroupVO> groups;
    
    public Integer getTotal() {
        return total;
    }
    
    public ArchiveVO setTotal(Integer total) {
        this.total = total;
        return this;
    }
    
    public List<ArchiveGroupVO> getGroups() {
        return groups;
    }
    
    public ArchiveVO setGroups(List<ArchiveGroupVO> groups) {
        this.groups = groups;
        return this;
    }
    
    @Override
    public String toString() {
        return "ArchiveVO{" +
                "total=" + total +
                ", groups=" + groups +
                '}';
    }
    
}
