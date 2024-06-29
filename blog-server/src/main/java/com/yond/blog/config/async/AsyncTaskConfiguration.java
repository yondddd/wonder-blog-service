package com.yond.blog.config.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class AsyncTaskConfiguration implements AsyncConfigurer {

    // @Async使用虚拟线程
    @Override
    public Executor getAsyncExecutor() {
        return new TaskExecutorAdapter(Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("virtual-async#", 1).factory()));
    }

}
