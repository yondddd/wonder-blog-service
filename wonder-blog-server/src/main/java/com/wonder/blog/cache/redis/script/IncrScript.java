package com.wonder.blog.cache.redis.script;

/**
 * @Author: Yond
 */
public interface IncrScript {

    /**
     * 自增指定数目，如果key是第一次设置，则设置过期时间
     */
    String INCR_EXPIRE = "local current = redis.call('incrBy',KEYS[1],ARGV[1]);" +
            " if current == tonumber(ARGV[1]) then" +
            " local t = redis.call('ttl',KEYS[1]);" +
            " if t == -1 then " +
            " redis.call('expire',KEYS[1],ARGV[2])" +
            " end;" +
            " end;" +
            " return current";

}
