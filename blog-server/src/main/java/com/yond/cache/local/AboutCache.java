package com.yond.cache.local;

import com.github.benmanes.caffeine.cache.Cache;

import java.util.Map;

/**
 * @author yond
 * @date 6/21/2024
 * @description about info cache
 */
public class AboutCache {

    private final static String KEY = "allAbout";

    private final static Cache<String, Map<String, String>> cache = LocalCache.buildCache(1);


    public static Map<String, String> get() {
        return cache.getIfPresent(KEY);
    }

    public static void set(Map<String, String> aboutInfoMap) {
        cache.put(KEY, aboutInfoMap);
    }

    public static void del() {
        cache.invalidate(KEY);
    }

}
