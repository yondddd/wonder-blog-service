package com.yond.constant;

/**
 * @author yond
 * @date 6/21/2024
 * @description blog canstant
 */
public class BlogConstant {

    /**
     * 随机博客显示5条
     */
    public static final int RANDOM_BLOG_LIMIT_NUM = 5;
    /**
     * 最新推荐博客显示3条
     */
    public static final int NEW_BLOG_PAGE_SIZE = 3;
    /**
     * 每页显示5条博客简介
     */
    public static final int PAGE_SIZE = 5;
    /**
     * 博客简介列表排序方式
     */
    public static final String ORDER_BY = "is_top desc, create_time desc";
    /**
     * 私密博客提示
     */
    public static final String PRIVATE_BLOG_DESCRIPTION = "此文章受密码保护！";

}
