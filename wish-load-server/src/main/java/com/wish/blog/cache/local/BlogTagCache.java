package com.wish.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.wish.blog.entity.BlogTagDO;

import java.util.List;

/**
 * @Description:
 * @Author: yond
 */
public class BlogTagCache {

    private final static Cache<String, List<BlogTagDO>> cache = LocalCache.buildCache(1);
    private final static String ALL_KEY = "ALL";

    public static List<BlogTagDO> listAll() {
        return cache.getIfPresent(ALL_KEY);
    }

    public static void setAll(List<BlogTagDO> all) {
        cache.put(ALL_KEY, all);
    }

    public static void removeAll() {
        cache.invalidateAll();
    }

}
