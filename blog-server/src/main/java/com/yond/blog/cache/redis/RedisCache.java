package com.yond.blog.cache.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @Author Yond
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

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long increment(String key, long amount) {
        return redisTemplate.opsForValue().increment(key, amount);
    }

    public Long incrBy(String key, long amount, Integer seconds) {
        // todo lua做成原子性
        Long increment = redisTemplate.opsForValue().increment(key, amount);
        this.expire(key, seconds);
        return increment;
    }

    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public boolean expire(String key, Integer seconds) {
        return redisTemplate.expire(key, Duration.ofSeconds(seconds));
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


    /**
     * is set member
     */
    public Boolean sismember(String key, T member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * add member to set
     */
    public Long sadd(String key, T member) {
        return redisTemplate.opsForSet().add(key, member);
    }

    /**
     * remove member from set
     */
    public Long srem(String key, T member) {
        return redisTemplate.opsForSet().remove(key, member);
    }

    /**
     * set size
     */
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

}
