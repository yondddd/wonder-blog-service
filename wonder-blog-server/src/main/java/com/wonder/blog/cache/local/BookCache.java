package com.wonder.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.wonder.blog.entity.BookshelfDO;
import com.wonder.blog.entity.CategoryDO;

import java.util.List;

/**
 * @author yond
 * @date 11/16/2024
 * @description
 */
public class BookCache {

    private final static String KEY = "allBook";

    private final static Cache<String, List<BookshelfDO>> cache = LocalCache.buildCache(1);


    public static List<BookshelfDO> get() {
        return cache.getIfPresent(KEY);
    }

    public static void set(List<BookshelfDO> listAll) {
        cache.put(KEY, listAll);
    }

    public static void del() {
        cache.invalidate(KEY);
    }

}
