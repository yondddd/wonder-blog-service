package com.yond.blog.web.view.dto;

/**
 * @Description: UserAgent解析DTO
 * @Author: Yond
 */
public class UserAgentDTO {
    private String os;
    private String browser;
    
    public UserAgentDTO(String os, String browser) {
        this.os = os;
        this.browser = browser;
    }
    
    public UserAgentDTO() {
    }
    
    public String getOs() {
        return this.os;
    }
    
    public String getBrowser() {
        return this.browser;
    }
    
    public void setOs(String os) {
        this.os = os;
    }
    
    public void setBrowser(String browser) {
        this.browser = browser;
    }
    
    public String toString() {
        return "UserAgentDTO(os=" + this.getOs() + ", browser=" + this.getBrowser() + ")";
    }
}
