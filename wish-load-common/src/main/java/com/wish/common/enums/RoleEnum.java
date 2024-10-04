package com.wish.common.enums;

/**
 * @Author Yond
 */
public enum RoleEnum {


    ADMIN("ROLE_admin", "管理员"),
    ;

    private final String val;
    private final String desc;

    RoleEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    public String getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }

}
