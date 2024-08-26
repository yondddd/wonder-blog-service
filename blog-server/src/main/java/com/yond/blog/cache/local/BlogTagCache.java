package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.yond.blog.entity.BlogTagDO;

import java.util.List;

/**
 * @Description:
 * @Author: WangJieLong
 * @Date: 2024-08-26
 */
public class BlogTagCache {
    
    private final static Cache<String, List<BlogTagDO>> cache = LocalCache.buildCache(1);
    private final static String ALL_KEY = "ALL";
    
    public static List<BlogTagDO> listAll(){
        return cache.getIfPresent(ALL_KEY);
    }
    
    public static void setAll(List<BlogTagDO> all){
        cache.put(ALL_KEY,all);
    }
    
}
