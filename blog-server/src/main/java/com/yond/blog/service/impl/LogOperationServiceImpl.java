package com.yond.blog.service.impl;

import com.yond.blog.entity.LogOperationDO;
import com.yond.blog.mapper.LogOperationMapper;
import com.yond.blog.service.LogOperationService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 操作日志业务层实现
 * @Author: Naccl
 * @Date: 2020-11-30
 */
@Service
public class LogOperationServiceImpl implements LogOperationService {

    @Resource
    private LogOperationMapper logOperationMapper;

    @Override
    public List<LogOperationDO> getOperationLogListByDate(String startDate, String endDate) {
        return logOperationMapper.listByDate(startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOperationLog(LogOperationDO log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = UserAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        logOperationMapper.insertSelective(log);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteOperationLogById(Long id) {
        if (logOperationMapper.deleteById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }
}
