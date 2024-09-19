package com.yond.blog.schedule.task;

import com.yond.blog.cache.redis.BlogViewCache;
import com.yond.blog.service.BlogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @Description: Redis相关定时任务
 * @Author: Yond
 */
@Component
public class RedisSyncScheduleTask {
    
    @Resource
    private BlogViewCache blogViewCache;
    @Resource
    private BlogService blogService;
    
    /**
     * 从Redis同步博客文章浏览量到数据库
     */
    public void syncBlogViewsToDatabase() {
        Map<Long, Integer> viewMap = blogViewCache.getViewMap();
        Set<Long> keys = viewMap.keySet();
        for (Long key : keys) {
            Integer views = viewMap.get(key);
            blogService.updateViews(key, views);
        }
    }
    
}
