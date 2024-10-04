package com.wish.blog.service.impl;

import com.wish.blog.entity.LogScheduleJobDO;
import com.wish.blog.mapper.LogScheduleJobMapper;
import com.wish.blog.service.LogScheduleJobService;
import com.wish.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: Yond
 */
@Service
public class LogScheduleJobServiceImpl implements LogScheduleJobService {
    
    @Resource
    private LogScheduleJobMapper logScheduleJobMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSelective(LogScheduleJobDO jobLog) {
        if (logScheduleJobMapper.insertSelective(jobLog) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSelective(LogScheduleJobDO log) {
        if (logScheduleJobMapper.updateSelective(log) != 1) {
            throw new PersistenceException("日志更新失败");
        }
    }
    
    @Override
    public Pair<Integer, List<LogScheduleJobDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        Integer count = logScheduleJobMapper.countBy(startDate, endDate);
        if (count <= 0) {
            return Pair.of(count, Collections.emptyList());
        }
        List<LogScheduleJobDO> list = logScheduleJobMapper.pageBy(startDate, endDate, (pageNo - 1) * pageSize, pageSize);
        return Pair.of(count, list);
    }
    
}
