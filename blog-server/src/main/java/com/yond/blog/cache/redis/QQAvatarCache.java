package com.yond.blog.cache.redis;

import com.yond.common.constant.RedisKeyConstant;
import org.springframework.stereotype.Component;

/**
 * @Author Yond
 */
@Component
public class QQAvatarCache {

    private final RedisCache<String> redisCache;

    public QQAvatarCache(RedisCache<String> redisCache) {
        this.redisCache = redisCache;
    }

    public String get(String qq) {
        return redisCache.get(this.getKey(qq));
    }

    public void set(String qq, String avatar) {
        redisCache.set(this.getKey(qq), avatar);
    }

    private String getKey(String qq) {
        return RedisKeyConstant.buildKey(RedisKeyConstant.QQ_AVATAR, qq);
    }

}
