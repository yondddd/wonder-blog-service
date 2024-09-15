package com.yond.blog.mapper;

import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 访问日志持久层接口
 * @Author: Yond
 * @Date: 2020-12-04
 */
@Mapper
@Repository
public interface LogVisitMapper {

    List<LogVisitDO> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    int saveVisitLog(LogVisitDO log);

    int deleteVisitLogById(Long id);

    int countVisitLogByToday();
}
