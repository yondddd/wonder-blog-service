package com.yond.common.enums;

/**
 * @author yond
 * @date 7/21/2024
 * @description
 */
public enum SiteSettingEnum {

    BLOG_INFO(1, "博客信息"),

    PERSON_INFO(2, "个人信息"),

    BOTTOM_BADGE(3, "底部徽标"),

    THIRD_PARTY_KEY(4, "第三方服务key"),
    ;

    private final int val;
    private final String desc;

    SiteSettingEnum(int val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    public Integer getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }
}
