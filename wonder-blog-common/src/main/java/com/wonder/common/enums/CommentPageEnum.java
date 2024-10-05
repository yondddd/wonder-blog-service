package com.wonder.common.enums;

/**
 * 评论页面枚举类
 *
 * @Author: Yond
 */
public enum CommentPageEnum {

    UNKNOWN(-1, "UNKNOWN", "UNKNOWN"),

    BLOG(0, "", ""),

    ABOUT(1, "关于我", "/about"),

    FRIEND(2, "友人帐", "/friends"),
    ;

    private final Integer id;
    private final String title;
    private final String path;

    CommentPageEnum(Integer id, String title, String path) {
        this.id = id;
        this.title = title;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public static CommentPageEnum getByValue(Integer id) {
        if (id == null) {
            return null;
        }
        for (CommentPageEnum e : CommentPageEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

}
