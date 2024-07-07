package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.LoginLogDO;

import java.util.List;

/**
 * @Description: 登录日志持久层接口
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Mapper
@Repository
public interface LoginLogMapper {
	List<LoginLogDO> getLoginLogListByDate(String startDate, String endDate);

	int saveLoginLog(LoginLogDO log);

	int deleteLoginLogById(Long id);
}
