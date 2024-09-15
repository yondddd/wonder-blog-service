package com.yond.blog.mapper;

import com.yond.blog.entity.LogOperationDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 操作日志持久层接口
 * @Author: Yond
 * @Date: 2020-11-30
 */
@Mapper
@Repository
public interface LogOperationMapper {

    List<LogOperationDO> listByDate(String startDate, String endDate);

    int insertSelective(LogOperationDO logOperationDO);

    int deleteById(Long id);

}
