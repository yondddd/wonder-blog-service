package com.yond.blog.cache.redis;

import com.yond.common.constant.RedisKeyConstant;
import org.springframework.stereotype.Component;

/**
 * @Author Yond
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
    
    public Long incrBy(String key, Integer amount, Integer seconds) {
        return redisCache.incrBy(this.getKey(key), amount, seconds);
    }
    
    private String getKey(String key) {
        return RedisKeyConstant.buildKey(RedisKeyConstant.ACCESS_LIMIT, key);
    }
    
}
