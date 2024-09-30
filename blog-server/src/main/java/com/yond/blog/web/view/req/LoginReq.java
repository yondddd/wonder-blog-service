package com.yond.blog.web.view.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class LoginReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1471318037796858453L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginReq setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginReq setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "LoginReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
