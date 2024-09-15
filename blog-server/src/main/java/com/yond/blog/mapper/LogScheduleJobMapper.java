package com.yond.blog.mapper;

import com.yond.blog.entity.LogScheduleJobDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 定时任务日志持久层接口
 * @Author: Yond
 * @Date: 2020-11-01
 */
@Mapper
@Repository
public interface LogScheduleJobMapper {
    List<LogScheduleJobDO> getJobLogListByDate(String startDate, String endDate);

    int saveJobLog(LogScheduleJobDO jobLog);

    int deleteJobLogByLogId(Long logId);
}
