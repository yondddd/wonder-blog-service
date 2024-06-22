package com.yond.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.yond.entity.CategoryDO;

import java.util.List;

/**
 * @author yond
 * @date 6/22/2024
 * @description category cache
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
