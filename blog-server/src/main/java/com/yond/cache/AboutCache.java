package com.yond.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yond
 * @date 6/21/2024
 * @description about info cache
 */
@Component
public class AboutCache {

    private final static String KEY = "aboutInfoMap";

    private final Cache<String, Map<String, String>> cache = Caffeine.newBuilder()
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(1)
            .build();

    public Map<String, String> get() {
        return cache.getIfPresent(KEY);
    }

    public void set(Map<String, String> aboutInfoMap) {
        cache.put(KEY, aboutInfoMap);
    }

    public void del() {
        cache.invalidate(KEY);
    }

}
