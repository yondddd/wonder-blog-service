package com.yond.blog.service.impl;

import com.yond.blog.entity.ScheduleJobDO;
import com.yond.blog.mapper.ScheduleJobMapper;
import com.yond.blog.schedule.BlogSchedulingConfigurer;
import com.yond.blog.service.ScheduleJobService;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @Description: 定时任务业务层实现
 * @Author: Yond
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
    
    @Resource
    private ScheduleJobMapper schedulerJobMapper;
    @Resource
    private BlogSchedulingConfigurer blogSchedulingConfigurer;
    
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
    public ScheduleJobDO getJobById(Long jobId) {
        return schedulerJobMapper.getJobById(jobId);
    }
    
    @Override
    public Pair<Integer, List<ScheduleJobDO>> page(Integer pageNo, Integer pageSize) {
        Integer count = schedulerJobMapper.countBy();
        if (count <= 0) {
            return Pair.of(count, Collections.emptyList());
        }
        List<ScheduleJobDO> list = schedulerJobMapper.pageBy((pageNo - 1) * pageSize, pageSize);
        return Pair.of(count, list);
    }
    
}
