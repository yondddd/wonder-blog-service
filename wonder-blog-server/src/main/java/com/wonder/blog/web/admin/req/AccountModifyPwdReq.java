package com.wonder.blog.web.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class AccountModifyPwdReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -2915092810476190536L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public AccountModifyPwdReq setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountModifyPwdReq setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "AccountModifyPwdReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
