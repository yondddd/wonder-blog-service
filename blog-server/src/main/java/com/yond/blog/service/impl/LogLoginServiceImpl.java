package com.yond.blog.service.impl;

import com.yond.blog.entity.LogLoginDO;
import com.yond.blog.mapper.LogLoginMapper;
import com.yond.blog.service.LogLoginService;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.util.ip.IpAddressUtils;
import com.yond.blog.web.view.dto.UserAgentDTO;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description: 登录日志业务层实现
 * @Author: Yond
 */
@Service
public class LogLoginServiceImpl implements LogLoginService {
    
    @Resource
    private LogLoginMapper logLoginMapper;
    
    
    @Override
    public Pair<Integer, List<LogLoginDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        Integer count = logLoginMapper.countBy(startDate, endDate);
        if (count <= 0) {
            return Pair.of(count, Collections.emptyList());
        }
        List<LogLoginDO> list = logLoginMapper.pageBy(startDate, endDate, (pageNo - 1) * pageSize, pageSize);
        return Pair.of(count, list);
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveLoginLog(LogLoginDO log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = UserAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (logLoginMapper.insertSelective(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }
    
    @Override
    public int updateSelective(LogLoginDO log) {
        return logLoginMapper.updateSelective(log);
    }
}
