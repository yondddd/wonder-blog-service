package com.yond.blog.service.impl;

import com.yond.blog.entity.LogOperationDO;
import com.yond.blog.mapper.LogOperationMapper;
import com.yond.blog.service.LogOperationService;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.util.ip.IpAddressUtils;
import com.yond.blog.web.view.dto.UserAgentDTO;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description: 操作日志业务层实现
 * @Author: Yond
 */
@Service
public class LogOperationServiceImpl implements LogOperationService {
    
    @Resource
    private LogOperationMapper logOperationMapper;
    
    
    @Override
    public Pair<Integer, List<LogOperationDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        Integer count = logOperationMapper.countBy(startDate, endDate);
        if (count <= 0) {
            return Pair.of(count, Collections.emptyList());
        }
        List<LogOperationDO> list = logOperationMapper.pageBy(startDate, endDate, (pageNo - 1) * pageSize, pageSize);
        return Pair.of(count, list);
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
    
    @Override
    public int updateSelective(LogOperationDO log) {
        return logOperationMapper.updateSelective(log);
    }
    
}
