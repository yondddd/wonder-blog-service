package com.yond.blog.service;

import org.springframework.scheduling.annotation.Async;
import com.yond.blog.entity.OperationLogDO;

import java.util.List;

public interface OperationLogService {
	List<OperationLogDO> getOperationLogListByDate(String startDate, String endDate);

	@Async
	void saveOperationLog(OperationLogDO log);

	void deleteOperationLogById(Long id);
}
