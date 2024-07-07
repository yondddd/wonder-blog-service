package com.yond.blog.service;

import com.yond.blog.entity.VisitLogDO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface VisitLogService {
    List<VisitLogDO> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    @Async
    void saveVisitLog(VisitLogDO log);

    void deleteVisitLogById(Long id);

    int countVisitLogByToday();
}
