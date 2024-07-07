package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.ScheduleJobLogDO;

import java.util.List;

/**
 * @Description: 定时任务日志持久层接口
 * @Author: Naccl
 * @Date: 2020-11-01
 */
@Mapper
@Repository
public interface ScheduleJobLogMapper {
	List<ScheduleJobLogDO> getJobLogListByDate(String startDate, String endDate);

	int saveJobLog(ScheduleJobLogDO jobLog);

	int deleteJobLogByLogId(Long logId);
}
