package com.yond.blog.mapper;

import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.web.view.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 访问日志持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface LogVisitMapper {
    
    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();
    
    int countVisitLogByToday();
    
    int insertSelective(LogVisitDO log);
    
    int updateSelective(LogVisitDO log);
    
    Integer countBy(String uuid, Date startDate, Date endDate);
    
    List<LogVisitDO> pageBy(String uuid, Date startDate, Date endDate, Integer offset, Integer size);
    
}
