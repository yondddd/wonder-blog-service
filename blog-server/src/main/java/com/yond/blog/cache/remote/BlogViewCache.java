package com.yond.blog.cache.remote;

import com.yond.blog.cache.constant.RedisKeyConstant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yond
 * @date 6/29/2024
 * @description blog view
 */
@Component
public class BlogViewCache {

    private final RedisCache<Map<Long, Integer>> redisCache;

    public BlogViewCache(RedisCache<Map<Long, Integer>> redisCache) {
        this.redisCache = redisCache;
    }

    public boolean existViewMap() {
        return redisCache.exists(this.getKey());
    }

    public Map<Long, Integer> getViewMap() {
        return redisCache.get(getKey());
    }

    public synchronized void setViewMap(Map<Long, Integer> viewMap) {
        redisCache.set(getKey(), viewMap);
    }

    public void deleteViewMap() {
        redisCache.delete(getKey());
    }

    public Integer getBlogView(Long blogId) {
        Map<Long, Integer> viewMap = this.getViewMap();
        if (viewMap == null) {
            this.setBlogView(blogId, 0);
            return 0;
        }
        return viewMap.get(blogId);
    }

    public synchronized void setBlogView(Long blogId, Integer view) {
        Map<Long, Integer> viewMap = this.getViewMap();
        if (viewMap == null) {
            viewMap = new HashMap<>();
        }
        viewMap.put(blogId, view);
        this.setViewMap(viewMap);
    }

    public synchronized void deleteBlogView(Long blogId) {
        Map<Long, Integer> viewMap = this.getViewMap();
        if (viewMap == null) {
            return;
        }
        viewMap.remove(blogId);
        this.setViewMap(viewMap);
    }

    public synchronized void blogViewIncr(Long blogId, Integer increase) {
        Integer blogView = this.getBlogView(blogId);
        blogView += increase;
        this.setBlogView(blogId, blogView);
    }

    private String getKey() {
        return RedisKeyConstant.BLOG_VIEW_MAP;
    }

}
