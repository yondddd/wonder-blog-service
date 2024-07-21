package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yond.blog.cache.constant.LocalCacheConstant;

import java.util.concurrent.TimeUnit;

public class LocalCache {

    public static <K, V> Cache<K, V> buildCache(int maxSize) {
        return Caffeine.newBuilder()
                .expireAfterWrite(LocalCacheConstant.getExpireSecond(), TimeUnit.SECONDS)
                .maximumSize(maxSize)
                .scheduler(LocalCacheConstant.SCHEDULER)
                .executor(LocalCacheConstant.EXECUTOR)
                .build();
    }

    public static <K, V> Cache<K, V> buildCache(int maxSize, int expireSecond) {
        return Caffeine.newBuilder()
                .expireAfterWrite(expireSecond, TimeUnit.SECONDS)
                .maximumSize(maxSize)
                .scheduler(LocalCacheConstant.SCHEDULER)
                .executor(LocalCacheConstant.EXECUTOR)
                .build();
    }

}