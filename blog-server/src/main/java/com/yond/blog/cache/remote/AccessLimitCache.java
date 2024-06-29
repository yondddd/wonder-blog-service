package com.yond.blog.cache.remote;

import com.yond.blog.cache.constant.RedisKeyConstant;
import org.springframework.stereotype.Component;

/**
 * @author yond
 * @date 6/29/2024
 * @description access limit
 */
@Component
public class AccessLimitCache {

    private final RedisCache<Integer> redisCache;

    public AccessLimitCache(RedisCache<Integer> redisCache) {
        this.redisCache = redisCache;
    }

    public Integer getCount(String key) {
        return redisCache.scard(this.getKey(key)).intValue();
    }

    public void increment(String key, Integer amount) {
        redisCache.increment(this.getKey(key), amount);
    }

    public void incrBy(String key, Integer amount, Integer seconds) {
        redisCache.incrBy(this.getKey(key), amount, seconds);
    }

    private String getKey(String key) {
        return RedisKeyConstant.buildKey(RedisKeyConstant.ACCESS_LIMIT, key);
    }

}
