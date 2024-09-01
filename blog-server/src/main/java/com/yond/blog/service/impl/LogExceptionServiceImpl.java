package com.yond.blog.service.impl;

import com.yond.blog.entity.LogExceptionDO;
import com.yond.blog.mapper.LogExceptionMapper;
import com.yond.blog.service.LogExceptionService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import com.yond.common.exception.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 异常日志业务层实现
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Service
public class LogExceptionServiceImpl implements LogExceptionService {
    @Autowired
    LogExceptionMapper logExceptionMapper;

    @Override
    public List<LogExceptionDO> getExceptionLogListByDate(String startDate, String endDate) {
        return logExceptionMapper.getExceptionLogListByDate(startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveExceptionLog(LogExceptionDO log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = UserAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (logExceptionMapper.insertSelective(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteExceptionLogById(Long id) {
        if (logExceptionMapper.deleteExceptionLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }
}
