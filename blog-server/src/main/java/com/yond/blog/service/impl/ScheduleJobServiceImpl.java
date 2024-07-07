package com.yond.blog.service.impl;

import com.yond.blog.entity.ScheduleJobDO;
import com.yond.blog.entity.ScheduleJobLogDO;
import com.yond.blog.mapper.ScheduleJobLogMapper;
import com.yond.blog.mapper.ScheduleJobMapper;
import com.yond.blog.service.ScheduleJobService;
import com.yond.blog.util.quartz.ScheduleUtils;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.PostConstruct;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 定时任务业务层实现
 * @Author: Naccl
 * @Date: 2020-11-01
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    ScheduleJobMapper schedulerJobMapper;
    @Autowired
    ScheduleJobLogMapper scheduleJobLogMapper;
    @Autowired
    Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJobDO> scheduleJobList = getJobList();
        for (ScheduleJobDO scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public List<ScheduleJobDO> getJobList() {
        return schedulerJobMapper.getJobList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveJob(ScheduleJobDO scheduleJob) {
        if (schedulerJobMapper.saveJob(scheduleJob) != 1) {
            throw new PersistenceException("添加失败");
        }
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJob(ScheduleJobDO scheduleJob) {
        if (schedulerJobMapper.updateJob(scheduleJob) != 1) {
            throw new PersistenceException("更新失败");
        }
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJobById(Long jobId) {
        ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        if (schedulerJobMapper.deleteJobById(jobId) != 1) {
            throw new PersistenceException("删除失败");
        }
    }

    @Override
    public void runJobById(Long jobId) {
        ScheduleUtils.run(scheduler, schedulerJobMapper.getJobById(jobId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJobStatusById(Long jobId, Boolean status) {
        if (status) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        } else {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        if (schedulerJobMapper.updateJobStatusById(jobId, status) != 1) {
            throw new PersistenceException("修改失败");
        }
    }

    @Override
    public List<ScheduleJobLogDO> getJobLogListByDate(String startDate, String endDate) {
        return scheduleJobLogMapper.getJobLogListByDate(startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveJobLog(ScheduleJobLogDO jobLog) {
        if (scheduleJobLogMapper.saveJobLog(jobLog) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJobLogByLogId(Long logId) {
        if (scheduleJobLogMapper.deleteJobLogByLogId(logId) != 1) {
            throw new PersistenceException("日志删除失败");
        }
    }
}
