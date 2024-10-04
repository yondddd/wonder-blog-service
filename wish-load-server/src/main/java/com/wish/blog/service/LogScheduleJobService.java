package com.wish.blog.service;

import com.wish.blog.entity.LogScheduleJobDO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.List;

/**
 * @Author: Yond
 */
public interface LogScheduleJobService {
    
    void insertSelective(LogScheduleJobDO log);
    
    void updateSelective(LogScheduleJobDO log);
    
    Pair<Integer, List<LogScheduleJobDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize);
    
}
