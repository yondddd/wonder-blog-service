package com.yond.blog.schedule.task;

import com.yond.blog.cache.remote.BlogViewCache;
import com.yond.blog.service.BlogService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @Description: Redis相关定时任务
 * @Author: Naccl
 * @Date: 2020-11-02
 */
@Component
public class RedisSyncScheduleTask {

    private final BlogViewCache blogViewCache;
    private final BlogService blogService;

    public RedisSyncScheduleTask(BlogViewCache blogViewCache, BlogService blogService) {
        this.blogViewCache = blogViewCache;
        this.blogService = blogService;
    }

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
