package com.wish.blog.mapper;

import com.wish.blog.entity.ScheduleJobDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 定时任务持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface ScheduleJobMapper {

    List<ScheduleJobDO> listAllByStatus(@Param("status") Integer status);

    int insertSelective(ScheduleJobDO job);

    int updateSelective(ScheduleJobDO job);

    Integer countBy();

    List<ScheduleJobDO> pageBy(Integer offset, Integer size);

}
