package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.yond.blog.model.vo.FriendInfo;

/**
 * @author yond
 * @date 6/29/2024
 * @description fried cache
 */
public class FriendCache {

    private final static String KEY = "friendInfo";

    private final static Cache<String, FriendInfo> cache = LocalCache.buildCache(1);


    public static FriendInfo get() {
        return cache.getIfPresent(KEY);
    }

    public static void set(FriendInfo info) {
        cache.put(KEY, info);
    }

    public static void del() {
        cache.invalidate(KEY);
    }

}
