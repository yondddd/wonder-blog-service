package com.wish.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.wish.blog.entity.FriendDO;

import java.util.List;

/**
 * @Author Yond
 */
public class FriendCache {

    private final static String KEY = "friendAll";

    private final static Cache<String, List<FriendDO>> cache = LocalCache.buildCache(1);


    public static List<FriendDO> getAll() {
        return cache.getIfPresent(KEY);
    }

    public static void setAll(List<FriendDO> data) {
        cache.put(KEY, data);
    }

    public static void delAll() {
        cache.invalidate(KEY);
    }

}
