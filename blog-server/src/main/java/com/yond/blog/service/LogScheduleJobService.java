package com.yond.blog.service;

import com.yond.blog.entity.LogScheduleJobDO;

import java.util.List;

/**
 * @Author: Yond
 */
public interface LogScheduleJobService {
    
    List<LogScheduleJobDO> getJobLogListByDate(String startDate, String endDate);
    
    void saveJobLog(LogScheduleJobDO log);
    
    void deleteJobLogByLogId(Long logId);
    
}
