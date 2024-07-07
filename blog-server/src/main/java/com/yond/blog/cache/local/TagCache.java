package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.yond.blog.entity.TagDO;

import java.util.List;

/**
 * @author yond
 * @date 6/22/2024
 * @description tag cache
 */
public class TagCache {

    private final static String KEY = "allTag";

    private final static Cache<String, List<TagDO>> cache = LocalCache.buildCache(1);

    public static List<TagDO> get() {
        return cache.getIfPresent(KEY);
    }

    public static void set(List<TagDO> tags) {
        cache.put(KEY, tags);
    }

    public static void del() {
        cache.invalidate(KEY);
    }

}
