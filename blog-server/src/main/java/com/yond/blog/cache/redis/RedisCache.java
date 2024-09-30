package com.yond.blog.cache.redis;

import com.yond.blog.cache.redis.script.IncrScript;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Yond
 */
@Component
public class RedisCache<T> {
    
    @Resource
    private RedisTemplate<String, T> redisTemplate;
    
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
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
    
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
    
    public Long increment(String key, long amount) {
        return redisTemplate.opsForValue().increment(key, amount);
    }
    
    public Long incrBy(String key, long amount, Integer seconds) {
        return redisTemplate.opsForValue().getOperations()
                .execute(RedisScript.of(IncrScript.INCR_EXPIRE), List.of(key), amount, seconds);
    }
    
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }
    
    public Boolean expire(String key, Integer seconds) {
        return redisTemplate.expire(key, Duration.ofSeconds(seconds));
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
    public Long sadd(String key, T... member) {
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
