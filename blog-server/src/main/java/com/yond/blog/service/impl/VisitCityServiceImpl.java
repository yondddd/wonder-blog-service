package com.yond.blog.service.impl;

import com.yond.blog.entity.VisitCityDO;
import com.yond.blog.mapper.VisitCityMapper;
import com.yond.blog.service.VisitCityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 城市访客数量统计业务层实现
 * @Author: Naccl
 * @Date: 2021-02-26
 */
@Service
public class VisitCityServiceImpl implements VisitCityService {

    @Resource
    private VisitCityMapper visitCityMapper;

    @Override
    public List<VisitCityDO> listAll() {
        return visitCityMapper.listAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(VisitCityDO cityVisitor) {
        visitCityMapper.saveCityVisitor(cityVisitor);
    }

}
