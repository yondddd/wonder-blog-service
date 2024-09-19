package com.yond.blog.service.impl;

import com.yond.blog.entity.LogScheduleJobDO;
import com.yond.blog.mapper.LogScheduleJobMapper;
import com.yond.blog.service.LogScheduleJobService;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Yond
 */
@Service
public class LogScheduleJobServiceImpl implements LogScheduleJobService {
    
    @Resource
    private LogScheduleJobMapper logScheduleJobMapper;
    
    @Override
    public List<LogScheduleJobDO> getJobLogListByDate(String startDate, String endDate) {
        return logScheduleJobMapper.getJobLogListByDate(startDate, endDate);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveJobLog(LogScheduleJobDO jobLog) {
        if (logScheduleJobMapper.saveJobLog(jobLog) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJobLogByLogId(Long logId) {
        if (logScheduleJobMapper.deleteJobLogByLogId(logId) != 1) {
            throw new PersistenceException("日志删除失败");
        }
    }
    
}
