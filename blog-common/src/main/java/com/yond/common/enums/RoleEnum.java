package com.yond.common.enums;

/**
 * @author yond
 * @date 7/5/2024
 * @description role
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
