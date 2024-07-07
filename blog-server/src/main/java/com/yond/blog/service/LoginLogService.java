package com.yond.blog.service;

import org.springframework.scheduling.annotation.Async;
import com.yond.blog.entity.LoginLogDO;

import java.util.List;

public interface LoginLogService {
	List<LoginLogDO> getLoginLogListByDate(String startDate, String endDate);

	@Async
	void saveLoginLog(LoginLogDO log);

	void deleteLoginLogById(Long id);
}
