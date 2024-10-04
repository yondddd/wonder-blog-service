package com.wish.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 9/20/2024
 * @description
 */
public class AccountLoginReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -6983814585659310089L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public AccountLoginReq setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountLoginReq setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "AccountLoginReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
