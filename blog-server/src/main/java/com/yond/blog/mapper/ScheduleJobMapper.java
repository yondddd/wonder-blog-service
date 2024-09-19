package com.yond.blog.mapper;

import com.yond.blog.entity.ScheduleJobDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 定时任务持久层接口
 * @Author: Yond
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
    
    Integer countBy();
    
    List<ScheduleJobDO> pageBy(Integer offset, Integer size);
    
}
