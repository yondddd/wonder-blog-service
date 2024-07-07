package com.yond.blog.service.impl;

import com.yond.common.exception.PersistenceException;
import com.yond.blog.entity.LoginLogDO;
import com.yond.blog.mapper.LoginLogMapper;
import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import com.yond.blog.service.LoginLogService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 登录日志业务层实现
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;
    @Autowired
    UserAgentUtils userAgentUtils;

    @Override
    public List<LoginLogDO> getLoginLogListByDate(String startDate, String endDate) {
        return loginLogMapper.getLoginLogListByDate(startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveLoginLog(LoginLogDO log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (loginLogMapper.saveLoginLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLoginLogById(Long id) {
        if (loginLogMapper.deleteLoginLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }
}
