package com.yond.blog.service;

import com.yond.blog.entity.LogOperationDO;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface LogOperationService {
    List<LogOperationDO> getOperationLogListByDate(String startDate, String endDate);

    @Async
    void saveOperationLog(LogOperationDO log);

    void deleteOperationLogById(Long id);
}
