package com.wonder.blog.cache.redis;

import com.wonder.common.constant.RedisKeyConstant;
import org.springframework.stereotype.Component;

/**
 * @Author Yond
 */
@Component
public class VisitCache {

    private final RedisCache<String> redisCache;

    public VisitCache(RedisCache<String> redisCache) {
        this.redisCache = redisCache;
    }

    public boolean identityExist(String identification) {
        return redisCache.sismember(this.getKey(), identification);
    }

    public void addIdentity(String identification) {
        redisCache.sadd(this.getKey(), identification);
    }

    public void removeIdentity(String identification) {
        redisCache.srem(this.getKey(), identification);
    }

    public int identitySize() {
        return redisCache.scard(this.getKey()).intValue();
    }

    public void clearIdentity() {
        redisCache.delete(this.getKey());
    }

    private String getKey() {
        return RedisKeyConstant.IDENTIFICATION_SET;
    }

}
