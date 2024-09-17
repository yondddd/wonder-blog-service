package com.yond.blog.service;

import com.yond.blog.entity.LogExceptionDO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;

public interface LogExceptionService {
    
    Pair<Integer, List<LogExceptionDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize);
    
    @Async
    void saveExceptionLog(LogExceptionDO log);
    
    int updateSelective(LogExceptionDO log);
    
}
