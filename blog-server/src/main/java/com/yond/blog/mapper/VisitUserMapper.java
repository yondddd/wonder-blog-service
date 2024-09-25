package com.yond.blog.mapper;

import com.yond.blog.entity.VisitUserDO;
import com.yond.blog.web.view.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 访客统计持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface VisitUserMapper {
    
    List<String> getNewVisitorIpSourceByYesterday();
    
    int hasUUID(String uuid);
    
    int saveVisitor(VisitUserDO visitor);
    
    int updatePVAndLastTimeByUUID(VisitLogUuidTime dto);
    
    int deleteVisitorById(Long id);
    
    Integer countBy(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    List<VisitUserDO> pageBy(@Param("offset") int offset, @Param("size") Integer size, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
