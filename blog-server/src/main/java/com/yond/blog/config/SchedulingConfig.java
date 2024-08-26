package com.yond.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.concurrent.Executor;

@Configuration
public class SchedulingConfig {

    @Bean
    public Executor customThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1); // Set your core pool size
        executor.setMaxPoolSize(1); // Set your max pool size
        executor.setThreadNamePrefix("scheduler-job-thread");
        executor.initialize();
        return executor;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setAutoStartup(false); // Disable Quartz auto-start
        // Configure Quartz to use custom thread pool
        schedulerFactoryBean.setTaskExecutor(customThreadPool());
        // You may configure other properties of schedulerFactoryBean here
        return schedulerFactoryBean;
    }

}
