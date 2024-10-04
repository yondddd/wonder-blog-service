package com.wish.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    
    private final AtomicInteger threadNum = new AtomicInteger(1);
    
    private final String prefix;
    
    private final boolean daemon;
    
    public NamedThreadFactory(String prefix) {
        this(prefix, true);
    }
    
    public NamedThreadFactory(String prefix, boolean daemon) {
        this.prefix = prefix + "-";
        this.daemon = daemon;
    }
    
    @Override
    public Thread newThread(Runnable runnable) {
        String name = prefix + threadNum.getAndIncrement();
        Thread thread = new Thread(runnable);
        thread.setDaemon(daemon);
        thread.setName(name);
        return thread;
    }
    
}