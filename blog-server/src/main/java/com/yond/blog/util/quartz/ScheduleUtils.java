package com.yond.blog.util.quartz;

import com.yond.blog.entity.ScheduleJobDO;
import org.quartz.*;

/**
 * @Description: 定时任务操作工具类
 * @Author: Naccl
 * @Date: 2020-11-01
 */
public class ScheduleUtils {
    private final static String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJobDO ScheduleJob) {
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJobInfo.class).withIdentity(getJobKey(ScheduleJob.getJobId())).build();
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(ScheduleJob.getCron()).withMisfireHandlingInstructionDoNothing();
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(ScheduleJob.getJobId())).withSchedule(scheduleBuilder).build();
            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJobDO.JOB_PARAM_KEY, ScheduleJob);
            scheduler.scheduleJob(jobDetail, trigger);
            if (!ScheduleJob.getStatus()) {
                pauseJob(scheduler, ScheduleJob.getJobId());
            }
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJobDO ScheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(ScheduleJob.getJobId());
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(ScheduleJob.getCron()).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = getCronTrigger(scheduler, ScheduleJob.getJobId());
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //参数
            trigger.getJobDataMap().put(ScheduleJobDO.JOB_PARAM_KEY, ScheduleJob);
            scheduler.rescheduleJob(triggerKey, trigger);
            if (!ScheduleJob.getStatus()) {
                pauseJob(scheduler, ScheduleJob.getJobId());
            }
        } catch (SchedulerException e) {
            throw new RuntimeException("更新定时任务失败", e);
        }
    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, ScheduleJobDO ScheduleJob) {
        try {
            //参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleJobDO.JOB_PARAM_KEY, ScheduleJob);
            scheduler.triggerJob(getJobKey(ScheduleJob.getJobId()), dataMap);
        } catch (SchedulerException e) {
            throw new RuntimeException("立即执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }
}
