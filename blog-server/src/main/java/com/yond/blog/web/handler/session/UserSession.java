package com.yond.blog.web.handler.session;

/**
 * @Author Yond
 */
public class UserSession {
    
    private Long userId;
    private String guid;
    private String userName;
    
    public static UserSession custom() {
        return new UserSession();
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public UserSession setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    
    public String getGuid() {
        return guid;
    }
    
    public UserSession setGuid(String guid) {
        this.guid = guid;
        return this;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public UserSession setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    
    @Override
    public String toString() {
        return "UserSession{" +
                "userId=" + userId +
                ", guid='" + guid + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
