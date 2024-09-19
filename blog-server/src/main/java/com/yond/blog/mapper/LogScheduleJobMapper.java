package com.yond.blog.mapper;

import com.yond.blog.entity.LogScheduleJobDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 定时任务日志持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface LogScheduleJobMapper {
    
    int insertSelective(LogScheduleJobDO log);
    
    int updateSelective(LogScheduleJobDO log);
    
    Integer countBy(Date startDate, Date endDate);
    
    List<LogScheduleJobDO> pageBy(Date startDate, Date endDate, Integer offset, Integer size);
    
}
