package com.yond.blog.service.impl;

import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.mapper.LogVisitMapper;
import com.yond.blog.service.LogVisitService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 访问日志业务层实现
 * @Author: Naccl
 * @Date: 2020-12-04
 */
@Service
public class LogVisitServiceImpl implements LogVisitService {

    @Resource
    private LogVisitMapper logVisitMapper;

    @Override
    public List<LogVisitDO> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate) {
        return logVisitMapper.getVisitLogListByUUIDAndDate(uuid, startDate, endDate);
    }

    @Override
    public List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday() {
        return logVisitMapper.getUUIDAndCreateTimeByYesterday();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveVisitLog(LogVisitDO log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = UserAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (logVisitMapper.saveVisitLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteVisitLogById(Long id) {
        if (logVisitMapper.deleteVisitLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }

    @Override
    public int countVisitLogByToday() {
        return logVisitMapper.countVisitLogByToday();
    }
}
