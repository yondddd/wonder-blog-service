package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.VisitLogDO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;

import java.util.List;

/**
 * @Description: 访问日志持久层接口
 * @Author: Naccl
 * @Date: 2020-12-04
 */
@Mapper
@Repository
public interface VisitLogMapper {
	List<VisitLogDO> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

	List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

	int saveVisitLog(VisitLogDO log);

	int deleteVisitLogById(Long id);

	int countVisitLogByToday();
}
