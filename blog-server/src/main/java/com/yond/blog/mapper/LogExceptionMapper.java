package com.yond.blog.mapper;

import com.yond.blog.entity.LogExceptionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 异常日志持久层接口
 * @Author: Yond
 * @Date: 2020-12-03
 */
@Mapper
@Repository
public interface LogExceptionMapper {
    
    int insertSelective(LogExceptionDO log);
    
    int updateSelective(LogExceptionDO log);
    
    Integer countBy(Date startDate, Date endDate);
    
    List<LogExceptionDO> pageBy(Date startDate, Date endDate, Integer offset, Integer size);
    
}
