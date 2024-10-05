package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Yond
 */
public class ArchiveGroupVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1548820301421955152L;
    
    private String dateGroup;
    private List<ArchiveBlogVO> blogs;
    
    public String getDateGroup() {
        return dateGroup;
    }
    
    public ArchiveGroupVO setDateGroup(String dateGroup) {
        this.dateGroup = dateGroup;
        return this;
    }
    
    public List<ArchiveBlogVO> getBlogs() {
        return blogs;
    }
    
    public ArchiveGroupVO setBlogs(List<ArchiveBlogVO> blogs) {
        this.blogs = blogs;
        return this;
    }
    
    @Override
    public String toString() {
        return "ArchiveGroupVO{" +
                "dateGroup='" + dateGroup + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
