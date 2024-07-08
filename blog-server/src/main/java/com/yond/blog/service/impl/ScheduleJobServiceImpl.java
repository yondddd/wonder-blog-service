package com.yond.blog.service.impl;

import com.yond.blog.entity.ScheduleJobDO;
import com.yond.blog.entity.ScheduleJobLogDO;
import com.yond.blog.mapper.ScheduleJobLogMapper;
import com.yond.blog.mapper.ScheduleJobMapper;
import com.yond.blog.schedule.BlogSchedulingConfigurer;
import com.yond.blog.service.ScheduleJobService;
import com.yond.common.exception.PersistenceException;
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

    private final ScheduleJobMapper schedulerJobMapper;
    private final ScheduleJobLogMapper scheduleJobLogMapper;
    private final BlogSchedulingConfigurer blogSchedulingConfigurer;

    public ScheduleJobServiceImpl(ScheduleJobMapper schedulerJobMapper, ScheduleJobLogMapper scheduleJobLogMapper, BlogSchedulingConfigurer blogSchedulingConfigurer) {
        this.schedulerJobMapper = schedulerJobMapper;
        this.scheduleJobLogMapper = scheduleJobLogMapper;
        this.blogSchedulingConfigurer = blogSchedulingConfigurer;
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
        blogSchedulingConfigurer.addJob(scheduleJob);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJob(ScheduleJobDO scheduleJob) {
        if (schedulerJobMapper.updateJob(scheduleJob) != 1) {
            throw new PersistenceException("更新失败");
        }
        blogSchedulingConfigurer.updateJob(scheduleJob);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJobById(Long jobId) {
        blogSchedulingConfigurer.removeJob(String.valueOf(jobId));
        if (schedulerJobMapper.deleteJobById(jobId) != 1) {
            throw new PersistenceException("删除失败");
        }
    }

    @Override
    public void runJobById(Long jobId) {
        blogSchedulingConfigurer.runJob(String.valueOf(jobId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJobStatusById(Long jobId, Boolean status) {
        ScheduleJobDO job = schedulerJobMapper.getJobById(jobId);
        if (status) {
            blogSchedulingConfigurer.removeJob(String.valueOf(jobId));
            blogSchedulingConfigurer.addJob(job);
        } else {
            blogSchedulingConfigurer.removeJob(String.valueOf(jobId));
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

    @Override
    public ScheduleJobDO getJobById(Long jobId) {
        return schedulerJobMapper.getJobById(jobId);
    }
}
