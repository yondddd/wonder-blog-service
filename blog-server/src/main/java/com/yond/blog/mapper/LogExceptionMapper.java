package com.yond.blog.mapper;

import com.yond.blog.entity.LogExceptionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 异常日志持久层接口
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Mapper
@Repository
public interface LogExceptionMapper {
    List<LogExceptionDO> getExceptionLogListByDate(String startDate, String endDate);

    int insertSelective(LogExceptionDO log);

    int deleteExceptionLogById(Long id);
}
