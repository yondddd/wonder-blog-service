package com.yond.cache.remote;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yond
 * @date 6/20/2024
 * @description redis cache
 */
@Component
public class RedisCache<T> {

    private final RedisTemplate redisTemplate;

    public RedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }


    public void set(String key, T value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * hash
     */
    public T hget(String key, Object field) {
        return (T) redisTemplate.opsForHash().get(key, field);
    }

    public void hset(String key, Object field, T value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

}
