package com.yond.blog.service;

import org.springframework.scheduling.annotation.Async;
import com.yond.blog.entity.LogLoginDO;

import java.util.List;

public interface LogLoginService {
    List<LogLoginDO> getLoginLogListByDate(String startDate, String endDate);

    @Async
    void saveLoginLog(LogLoginDO log);

    void deleteLoginLogById(Long id);
}
