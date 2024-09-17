package com.yond.blog.service.impl;

import com.yond.blog.entity.LogExceptionDO;
import com.yond.blog.mapper.LogExceptionMapper;
import com.yond.blog.service.LogExceptionService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description: 异常日志业务层实现
 * @Author: Yond
 * @Date: 2020-12-03
 */
@Service
public class LogExceptionServiceImpl implements LogExceptionService {
    
    @Resource
    private LogExceptionMapper logExceptionMapper;
    
    @Override
    public Pair<Integer, List<LogExceptionDO>> page(Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        Integer count = logExceptionMapper.countBy(startDate, endDate);
        if (count <= 0) {
            return Pair.of(count, Collections.emptyList());
        }
        List<LogExceptionDO> list = logExceptionMapper.pageBy(startDate, endDate, (pageNo - 1) * pageSize, pageSize);
        return Pair.of(count, list);
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
    
    @Override
    public int updateSelective(LogExceptionDO log) {
        return logExceptionMapper.updateSelective(log);
    }
    
}
