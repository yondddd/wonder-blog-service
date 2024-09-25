package com.yond.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: copyright
 * @Author: Yond
 */
public class Copyright implements Serializable {
    @Serial
    private static final long serialVersionUID = 3313951721464223460L;
    private String title;
    private String siteName;
    
    public Copyright() {
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getSiteName() {
        return this.siteName;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public String toString() {
        return "Copyright(title=" + this.getTitle() + ", siteName=" + this.getSiteName() + ")";
    }
}
