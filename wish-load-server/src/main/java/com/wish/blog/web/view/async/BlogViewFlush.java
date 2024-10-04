package com.wish.blog.web.view.async;

import com.wish.blog.service.BlogService;
import com.wish.common.thread.NamedThreadFactory;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description: 博客浏览定时刷盘  10s一次
 * @Author: Yond
 */
@Component
public class BlogViewFlush {
    
    @Resource
    private BlogService blogService;
    
    public BlogViewFlush() {
        EXECUTOR.scheduleWithFixedDelay(this::flush, 10, 10, TimeUnit.SECONDS);
    }
    
    private static final int batchSize = 10;
    private static final int interval = 10;
    
    private static final BlockingQueue<Long> queue = new LinkedBlockingQueue<>(1000);
    private static final ScheduledExecutorService EXECUTOR = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("blog-view"));
    
    public void blogViewIncr(Long blogId) {
        queue.add(blogId);
    }
    
    private void flush() {
        List<Long> ids = new ArrayList<>();
        drain(ids);
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        Map<Long, Integer> frequencyMap = new HashMap<>();
        Map<Integer, List<Long>> groupedByIdFrequency = new HashMap<>();
        for (Long id : ids) {
            int frequency = frequencyMap.getOrDefault(id, 0) + 1;
            frequencyMap.put(id, frequency);
        }
        for (Map.Entry<Long, Integer> entry : frequencyMap.entrySet()) {
            groupedByIdFrequency.computeIfAbsent(entry.getValue(), k -> new ArrayList<>()).add(entry.getKey());
        }
        // 按频率分组刷新
        for (Map.Entry<Integer, List<Long>> entry : groupedByIdFrequency.entrySet()) {
            blogService.incrBlogView(entry.getValue(), entry.getKey());
        }
    }
    
    private static void drain(List<Long> buffer) {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(interval);
        int size = 0;
        while (size < batchSize) {
            Long e = null;
            try {
                e = queue.poll(deadline - System.nanoTime(), TimeUnit.NANOSECONDS);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            if (e == null) {
                break;
            }
            buffer.add(e);
            ++size;
        }
    }
    
}
