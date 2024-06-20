package com.yond.config.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync  // 开启异步任务
public class AsyncTaskConfiguration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new TaskExecutorAdapter(Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("virtual-async#", 1).factory()));
    }

}
