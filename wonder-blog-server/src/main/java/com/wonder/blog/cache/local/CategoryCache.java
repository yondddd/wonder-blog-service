package com.wonder.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.wonder.blog.entity.CategoryDO;

import java.util.List;

/**
 * @Author Yond
 */
public class CategoryCache {

    private final static String KEY = "allCategory";

    private final static Cache<String, List<CategoryDO>> cache = LocalCache.buildCache(1);


    public static List<CategoryDO> get() {
        return cache.getIfPresent(KEY);
    }

    public static void set(List<CategoryDO> listAll) {
        cache.put(KEY, listAll);
    }

    public static void del() {
        cache.invalidate(KEY);
    }

}
