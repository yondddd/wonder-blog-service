package com.yond.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yond.entity.CategoryDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yond
 * @date 6/22/2024
 * @description category cache
 */
@Component
public class CategoryCache {

    private final static String KEY = "allCategory";

    private final Cache<String, List<CategoryDO>> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(1)
            .build();

    public List<CategoryDO> get() {
        return cache.getIfPresent(KEY);
    }

    public void set(List<CategoryDO> listAll) {
        cache.put(KEY, listAll);
    }

    public void del() {
        cache.invalidate(KEY);
    }

}
