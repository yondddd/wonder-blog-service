package com.yond.blog.service;

import org.springframework.scheduling.annotation.Async;
import com.yond.blog.entity.LogExceptionDO;

import java.util.List;

public interface LogExceptionService {
    List<LogExceptionDO> getExceptionLogListByDate(String startDate, String endDate);

    @Async
    void saveExceptionLog(LogExceptionDO log);

    void deleteExceptionLogById(Long id);
}
