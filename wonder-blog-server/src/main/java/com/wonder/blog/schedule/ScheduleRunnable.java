package com.wonder.blog.schedule;

import com.wonder.blog.util.spring.SpringContextUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @Description: 执行定时任务
 * @Author: Yond
 */
public class ScheduleRunnable implements Runnable {
    
    private final Long jobId;
    private final Object target;
    private final Method method;
    private final String params;
    
    public ScheduleRunnable(Long jobId, String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.jobId = jobId;
        this.target = SpringContextUtils.getBean(beanName);
        this.params = params;
        if (StringUtils.hasText(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }
    
    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.hasText(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            throw new RuntimeException("执行定时任务失败", e);
        }
    }
    
    public Long getJobId() {
        return this.jobId;
    }
    
    public Object getTarget() {
        return this.target;
    }
    
    public Method getMethod() {
        return this.method;
    }
    
    public String getParams() {
        return this.params;
    }
}
