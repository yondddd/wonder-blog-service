package com.yond.blog.web.handler.session;

/**
 * @author yond
 * @date 8/25/2024
 * @description
 */
public class UserSession {

    private String guid;
    private String userName;

    public static UserSession custom() {
        return new UserSession();
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
                "guid='" + guid + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
