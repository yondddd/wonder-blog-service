package com.wish.blog.service;

import com.wish.blog.entity.LogLoginDO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;

public interface LogLoginService {
    
    Pair<Integer, List<LogLoginDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize);
    
    @Async
    void saveLoginLog(LogLoginDO log);
    
    int updateSelective(LogLoginDO log);
    
}
