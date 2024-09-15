package com.yond.blog.service.impl;

import com.yond.blog.entity.LogLoginDO;
import com.yond.blog.mapper.LogLoginMapper;
import com.yond.blog.service.LogLoginService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 登录日志业务层实现
 * @Author: Yond
 * @Date: 2020-12-03
 */
@Service
public class LogLoginServiceImpl implements LogLoginService {

    @Resource
    private LogLoginMapper logLoginMapper;

    @Override
    public List<LogLoginDO> getLoginLogListByDate(String startDate, String endDate) {
        return logLoginMapper.listByDate(startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveLoginLog(LogLoginDO log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = UserAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (logLoginMapper.saveLoginLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLoginLogById(Long id) {
        if (logLoginMapper.deleteLoginLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }
}
