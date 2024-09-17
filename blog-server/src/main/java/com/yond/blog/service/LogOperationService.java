package com.yond.blog.service;

import com.yond.blog.entity.LogOperationDO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;

public interface LogOperationService {
    
    Pair<Integer, List<LogOperationDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize);
    
    @Async
    void saveOperationLog(LogOperationDO log);
    
    int updateSelective(LogOperationDO log);
    
}
