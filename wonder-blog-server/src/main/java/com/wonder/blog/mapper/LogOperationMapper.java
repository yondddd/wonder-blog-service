package com.wonder.blog.mapper;

import com.wonder.blog.entity.LogOperationDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 操作日志持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface LogOperationMapper {

    int insertSelective(LogOperationDO log);

    int updateSelective(LogOperationDO log);

    Integer countBy(Date startDate, Date endDate);

    List<LogOperationDO> pageBy(Date startDate, Date endDate, Integer offset, Integer size);

}
