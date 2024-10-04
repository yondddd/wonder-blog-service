package com.wish.common.constant;

/**
 * JWT常量
 *
 * @Author: Yond
 */
public class JwtConstant {
    /**
     * 博主token前缀
     */
    public static final String ADMIN_PREFIX = "admin:";

    public static final String DEFAULT_SECRET = "blogYond";

    public static final String DEFAULT_CLIENT = "blogUser";

    public static final String TOKEN_HEADER = "Authorization";

    public static final String GUID_HEADER = "REQUEST_GUID_KEY";

    public static final long LOGIN_TIME = 24 * 60 * 60 * 1000;

    public static final long ONE_MONTH_TIME = 1000 * 3600 * 24 * 30L;

}
