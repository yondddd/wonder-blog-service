package com.wish.blog.mapper;

import com.wish.blog.entity.LogExceptionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 异常日志持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface LogExceptionMapper {

    int insertSelective(LogExceptionDO log);

    int updateSelective(LogExceptionDO log);

    Integer countBy(Date startDate, Date endDate);

    List<LogExceptionDO> pageBy(Date startDate, Date endDate, Integer offset, Integer size);

}
