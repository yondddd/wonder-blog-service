package com.yond.blog.schedule;

import com.yond.blog.entity.LogScheduleJobDO;
import com.yond.blog.entity.ScheduleJobDO;
import com.yond.blog.service.LogScheduleJobService;
import com.yond.blog.service.ScheduleJobService;
import com.yond.common.utils.json.util.JsonUtils;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: yond
 */
@Configuration
@EnableScheduling
public class BlogSchedulingConfigurer implements SchedulingConfigurer {

    @Resource
    private TaskScheduler taskScheduler;
    @Resource
    private LogScheduleJobService logScheduleJobService;
    @Resource
    @Lazy
    private ScheduleJobService scheduleJobService;

    private ScheduledTaskRegistrar taskRegistrar;

    private final Map<String, ScheduledTask> scheduledTasks = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogSchedulingConfigurer.class);

    @Override
    @Async
    public void configureTasks(@NotNull ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler);
        this.taskRegistrar = taskRegistrar;
        List<ScheduleJobDO> jobList = scheduleJobService.listJobs();
        for (ScheduleJobDO job : jobList) {
            addJob(job);
        }
    }

    public void addJob(ScheduleJobDO job) {
        ScheduledTaskWrapper task = null;
        try {
            task = new ScheduledTaskWrapper(new ScheduleRunnable(job.getId(), job.getBeanName(), job.getMethodName(), job.getParams()));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        ScheduledTask scheduledTask = taskRegistrar.scheduleCronTask(new CronTask(task, job.getCron()));
        scheduledTasks.put(String.valueOf(job.getId()), scheduledTask);
        LOGGER.info("<|>BlogSchedulingConfigurer_addJob<|>job:{}<|>", JsonUtils.toJson(job));
    }

    public void removeJob(String jobId) {
        ScheduledTask scheduledTask = scheduledTasks.remove(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel();
            LOGGER.info("<|>BlogSchedulingConfigurer_removeJob<|>jobId:{}<|>", JsonUtils.toJson(jobId));
        } else {
            LOGGER.error("<|>BlogSchedulingConfigurer_removeJob_notFound<|>jobId:{}<|>", JsonUtils.toJson(jobId));
        }
    }

    public void updateJob(ScheduleJobDO job) {
        this.removeJob(String.valueOf(job.getId()));
        this.addJob(job);
        LOGGER.info("<|>BlogSchedulingConfigurer_updateJob<|>job:{}<|>", JsonUtils.toJson(job));
    }

    public void runJob(String jobId) {
        ScheduledTask task = scheduledTasks.get(jobId);
        if (task != null) {
            task.getTask().getRunnable().run();
            LOGGER.info("<|>BlogSchedulingConfigurer_runJob<|>jobId:{}<|>", JsonUtils.toJson(jobId));
        }
    }


    private class ScheduledTaskWrapper implements Runnable {

        private final ScheduleRunnable task;

        ScheduledTaskWrapper(ScheduleRunnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            ScheduleJobDO job = scheduleJobService.getJobById(task.getJobId());
            long start = System.currentTimeMillis();
            LogScheduleJobDO jobLog = new LogScheduleJobDO();
            jobLog.setJobId(job.getId());
            jobLog.setBeanName(job.getBeanName());
            jobLog.setMethodName(job.getMethodName());
            jobLog.setParams(job.getParams());
            jobLog.setCreateTime(new Date(start));
            try {
                task.run();
                jobLog.setRunStatus(true);
            } catch (Exception e) {
                jobLog.setRunStatus(false);
                jobLog.setError(e.toString());
            } finally {
                long duration = System.currentTimeMillis() - start;
                jobLog.setDuration((int) duration);
                logScheduleJobService.insertSelective(jobLog);
            }
        }
    }

}
