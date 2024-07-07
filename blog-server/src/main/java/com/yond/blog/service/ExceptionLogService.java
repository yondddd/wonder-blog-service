package com.yond.blog.service;

import org.springframework.scheduling.annotation.Async;
import com.yond.blog.entity.ExceptionLogDO;

import java.util.List;

public interface ExceptionLogService {
	List<ExceptionLogDO> getExceptionLogListByDate(String startDate, String endDate);

	@Async
	void saveExceptionLog(ExceptionLogDO log);

	void deleteExceptionLogById(Long id);
}
