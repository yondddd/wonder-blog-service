package com.yond.common.enums;

/**
 * @Author Yond
 */
public enum SiteConfigTypeEnum {

    BLOG_INFO(1, "博客信息"),

    PROFILE(2, "个人信息"),

    BOTTOM_BADGE(3, "底部徽标"),

    FRIEND(4, "友链信息"),

    THIRD_PARTY_KEY(5, "第三方服务key"),

    ABOUT(6, "about"),
    ;

    private final int val;
    private final String desc;

    SiteConfigTypeEnum(int val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    public Integer getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }

    public static SiteConfigTypeEnum getByVal(Integer val) {
        for (SiteConfigTypeEnum item : SiteConfigTypeEnum.values()) {
            if (item.getVal().equals(val)) {
                return item;
            }
        }
        return null;
    }
}
