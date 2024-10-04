package com.wish.blog.mapper;

import com.wish.blog.entity.LogLoginDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 登录日志持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface LogLoginMapper {

    int insertSelective(LogLoginDO logLoginDO);

    int updateSelective(LogLoginDO logLoginDO);

    Integer countBy(Date startDate, Date endDate);

    List<LogLoginDO> pageBy(Date startDate, Date endDate, Integer offset, Integer size);
}
