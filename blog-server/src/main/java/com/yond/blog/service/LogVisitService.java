package com.yond.blog.service;

import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface LogVisitService {
    List<LogVisitDO> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    @Async
    void saveVisitLog(LogVisitDO log);

    void deleteVisitLogById(Long id);

    int countVisitLogByToday();
}
