package com.yond.common.constant;

/**
 * @Description: Redis key前缀
 * @Author: Yond
 */
public class RedisKeyConstant {

    public static final String SPLITTER = ":";
    /**
     * 博客访问量
     */
    public static final String BLOG_VIEW_MAP = "blogViewMap";
    /**
     * 访客标识码
     */
    public static final String IDENTIFICATION_SET = "identitySet";
    /**
     * QQ号与对应头像URL
     */
    public static final String QQ_AVATAR = "qqAvatar";
    /**
     * 访问限流
     */
    public static final String ACCESS_LIMIT = "accessLimit";


    public static String buildKey(String... part) {
        return String.join(SPLITTER, part);
    }

}
