package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;

import java.util.Map;

/**
 * @author yond
 * @date 6/22/2024
 * @description site setting cache
 */
public class SiteSettingCache {

    private final static String KEY = "allSiteSetting";

    private final static Cache<String, Map<String, Object>> cache = LocalCache.buildCache(1);

    public static Map<String, Object> get() {
        return cache.getIfPresent(KEY);
    }

    public static void set(Map<String, Object> data) {
        cache.put(KEY, data);
    }

    public static void del() {
        cache.invalidate(KEY);
    }

}
