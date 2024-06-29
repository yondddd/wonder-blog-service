package com.yond.blog.cache.constant;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.github.benmanes.caffeine.cache.Scheduler;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yond
 * @date 6/22/2024
 * @description
 */
public class LocalCacheConstant {

    private final static AtomicInteger NUM = new AtomicInteger(-1);

    private final static ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNamePrefix("local-cache-flush")
            .setDaemon(true)
            .setPriority(Thread.NORM_PRIORITY)
            .build();

    public static Executor EXECUTOR = new ThreadPoolExecutor(
            1,
            1,
            10,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(100)
            , THREAD_FACTORY,
            new ThreadPoolExecutor.DiscardOldestPolicy());


    public static Scheduler SCHEDULER = Scheduler.forScheduledExecutorService(Executors.newScheduledThreadPool(1));


    public static int getExpireSecond() {
        return 10 * 60 + 5 * NUM.incrementAndGet();
    }

}
