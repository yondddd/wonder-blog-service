package com.yond.blog.mapper;

import com.yond.blog.entity.OperationLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 操作日志持久层接口
 * @Author: Naccl
 * @Date: 2020-11-30
 */
@Mapper
@Repository
public interface OperationLogMapper {

	List<OperationLogDO> listByDate(String startDate, String endDate);

	int insertSelective(OperationLogDO operationLogDO);

	int deleteById(Long id);

}
