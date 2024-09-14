package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Scheduler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalCache {
    
    private final static AtomicInteger NUM = new AtomicInteger(-1);
    
    private final static ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        
        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread thread = new Thread(r, "local-cache-flush-" + threadNumber.getAndIncrement());
//            thread.setDaemon(true);
//            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    };
    
    private static final Executor EXECUTOR = new ThreadPoolExecutor(
            1,
            1,
            10,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(100)
            , THREAD_FACTORY,
            new ThreadPoolExecutor.DiscardOldestPolicy());
    
    private static final Scheduler SCHEDULER = Scheduler.forScheduledExecutorService(Executors.newScheduledThreadPool(1));
    
    
    private static int getExpireSecond() {
        return 10 * 60 + 5 * NUM.incrementAndGet();
    }
    
    public static <K, V> Cache<K, V> buildCache(int maxSize) {
        return Caffeine.newBuilder()
                .expireAfterWrite(getExpireSecond(), TimeUnit.SECONDS)
                .maximumSize(maxSize)
                .scheduler(SCHEDULER)
                .executor(EXECUTOR)
                .build();
    }
    
    public static <K, V> Cache<K, V> buildCache(int maxSize, int expireSecond) {
        return Caffeine.newBuilder()
                .expireAfterWrite(expireSecond, TimeUnit.SECONDS)
                .maximumSize(maxSize)
                .scheduler(SCHEDULER)
                .executor(EXECUTOR)
                .build();
    }
    
    public static <K, V> Cache<K, V> buildCache(int maxSize, CacheLoader<K, V> loader) {
        return Caffeine.newBuilder()
                .expireAfterWrite(getExpireSecond(), TimeUnit.SECONDS)
                .maximumSize(maxSize)
                .scheduler(SCHEDULER)
                .executor(EXECUTOR)
                .build(loader);
    }
    
}