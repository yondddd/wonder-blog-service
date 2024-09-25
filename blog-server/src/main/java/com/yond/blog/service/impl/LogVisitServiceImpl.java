package com.yond.blog.service.impl;

import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.mapper.LogVisitMapper;
import com.yond.blog.service.LogVisitService;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.util.ip.IpAddressUtils;
import com.yond.blog.web.view.dto.UserAgentDTO;
import com.yond.blog.web.view.dto.VisitLogUuidTime;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description: 访问日志业务层实现
 * @Author: Yond
 */
@Service
public class LogVisitServiceImpl implements LogVisitService {
    
    @Resource
    private LogVisitMapper logVisitMapper;
    
    @Override
    public Pair<Integer, List<LogVisitDO>> page(String uuid, Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        Integer count = logVisitMapper.countBy(uuid, startDate, endDate);
        if (count <= 0) {
            return Pair.of(count, Collections.emptyList());
        }
        List<LogVisitDO> list = logVisitMapper.pageBy(uuid, startDate, endDate, (pageNo - 1) * pageSize, pageSize);
        return Pair.of(count, list);
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
        if (logVisitMapper.insertSelective(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }
    
    @Override
    public int updateSelective(LogVisitDO log) {
        return logVisitMapper.updateSelective(log);
    }
    
    @Override
    public int countVisitLogByToday() {
        return logVisitMapper.countVisitLogByToday();
    }
}
