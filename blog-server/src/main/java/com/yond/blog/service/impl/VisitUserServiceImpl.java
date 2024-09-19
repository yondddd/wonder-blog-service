package com.yond.blog.service.impl;

import com.yond.blog.cache.redis.VisitCache;
import com.yond.blog.entity.VisitUserDO;
import com.yond.blog.mapper.VisitUserMapper;
import com.yond.blog.service.VisitUserService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.UserAgentUtils;
import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 访客统计业务层实现
 * @Author: Yond
 */
@Service
public class VisitUserServiceImpl implements VisitUserService {

    @Resource
    private VisitUserMapper visitUserMapper;
    @Resource
    private VisitCache visitCache;


    @Override
    public Pair<Integer, List<VisitUserDO>> page(Integer pageNo, Integer pageSize, Date startDate, Date endDate) {
        Integer count = visitUserMapper.countBy(startDate, endDate);
        if (count == 0) {
            return Pair.of(0, new ArrayList<>());
        }
        List<VisitUserDO> data = visitUserMapper.pageBy((pageNo - 1) * pageSize, pageSize, startDate, endDate);
        return Pair.of(count, data);
    }

    @Override
    public List<String> getNewVisitorIpSourceByYesterday() {
        return visitUserMapper.getNewVisitorIpSourceByYesterday();
    }

    @Override
    public boolean hasUUID(String uuid) {
        return visitUserMapper.hasUUID(uuid) != 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveVisitor(VisitUserDO visitor) {
        String ipSource = IpAddressUtils.getCityInfo(visitor.getIp());
        UserAgentDTO userAgentDTO = UserAgentUtils.parseOsAndBrowser(visitor.getUserAgent());
        visitor.setIpSource(ipSource);
        visitor.setOs(userAgentDTO.getOs());
        visitor.setBrowser(userAgentDTO.getBrowser());
        if (visitUserMapper.saveVisitor(visitor) != 1) {
            throw new PersistenceException("访客添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePVAndLastTimeByUUID(VisitLogUuidTime dto) {
        visitUserMapper.updatePVAndLastTimeByUUID(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteVisitor(Long id, String uuid) {
        //删除Redis中该访客的uuid
        visitCache.removeIdentity(uuid);
        if (visitUserMapper.deleteVisitorById(id) != 1) {
            throw new PersistenceException("删除访客失败");
        }
    }
}
