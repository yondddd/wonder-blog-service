package com.wonder.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.wonder.blog.entity.SiteConfigDO;

import java.util.List;

/**
 * @Author Yond
 */
public class SiteSettingCache {

    private final static String KEY = "allSiteSetting";

    private final static Cache<String, List<SiteConfigDO>> cache = LocalCache.buildCache(1);

    public static List<SiteConfigDO> get() {
        return cache.getIfPresent(KEY);
    }

    public static void set(List<SiteConfigDO> data) {
        cache.put(KEY, data);
    }

    public static void del() {
        cache.invalidate(KEY);
    }

}
