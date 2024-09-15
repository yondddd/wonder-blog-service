package com.yond.blog.mapper;

import com.yond.blog.entity.ScheduleJobDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 定时任务持久层接口
 * @Author: Yond
 * @Date: 2020-11-01
 */
@Mapper
@Repository
public interface ScheduleJobMapper {

    List<ScheduleJobDO> getJobList();

    ScheduleJobDO getJobById(Long jobId);

    int saveJob(ScheduleJobDO scheduleJob);

    int updateJob(ScheduleJobDO scheduleJob);

    int deleteJobById(Long jobId);

    int updateJobStatusById(Long jobId, Boolean status);
}
