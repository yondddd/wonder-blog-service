package com.yond.blog.mapper;

import com.yond.blog.entity.VisitUserDO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 访客统计持久层接口
 * @Author: Naccl
 * @Date: 2021-01-31
 */
@Mapper
@Repository
public interface VisitUserMapper {

    List<VisitUserDO> listByDate(String startDate, String endDate);

    List<String> getNewVisitorIpSourceByYesterday();

    int hasUUID(String uuid);

    int saveVisitor(VisitUserDO visitor);

    int updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

    int deleteVisitorById(Long id);

    Integer countBy(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<VisitUserDO> pageBy(@Param("offset") int offset, @Param("size") Integer size, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
