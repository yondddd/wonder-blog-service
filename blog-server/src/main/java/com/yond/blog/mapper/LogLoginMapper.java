package com.yond.blog.mapper;

import com.yond.blog.entity.LogLoginDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 登录日志持久层接口
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Mapper
@Repository
public interface LogLoginMapper {

    List<LogLoginDO> listByDate(String startDate, String endDate);

    int saveLoginLog(LogLoginDO log);

    int deleteLoginLogById(Long id);
}
