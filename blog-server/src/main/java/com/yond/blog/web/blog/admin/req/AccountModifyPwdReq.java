package com.yond.blog.web.blog.admin.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class AccountModifyPwdReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -2915092810476190536L;

    private String userName;
    private String pwd;

    public String getUserName() {
        return userName;
    }

    public AccountModifyPwdReq setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public AccountModifyPwdReq setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    @Override
    public String toString() {
        return "AccountModifyPwdReq{" +
                "userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
