package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.ExceptionLogDO;

import java.util.List;

/**
 * @Description: 异常日志持久层接口
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Mapper
@Repository
public interface ExceptionLogMapper {
	List<ExceptionLogDO> getExceptionLogListByDate(String startDate, String endDate);

	int saveExceptionLog(ExceptionLogDO log);

	int deleteExceptionLogById(Long id);
}
