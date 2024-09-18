package com.yond.blog.service;

import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;

public interface LogVisitService {
    
    Pair<Integer, List<LogVisitDO>> page(String uuid, Date startDate, Date endDate, Integer pageNo, Integer pageSize);
    
    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();
    
    @Async
    void saveVisitLog(LogVisitDO log);
    
    int updateSelective(LogVisitDO log);
    
    int countVisitLogByToday();
}
