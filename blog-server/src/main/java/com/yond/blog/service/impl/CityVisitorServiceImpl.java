package com.yond.blog.service.impl;

import com.yond.blog.entity.CityVisitorDO;
import com.yond.blog.mapper.CityVisitorMapper;
import com.yond.blog.service.CityVisitorService;
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
public class CityVisitorServiceImpl implements CityVisitorService {

    @Resource
    private CityVisitorMapper cityVisitorMapper;

    @Override
    public List<CityVisitorDO> listAll() {
        return cityVisitorMapper.listAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(CityVisitorDO cityVisitor) {
        cityVisitorMapper.saveCityVisitor(cityVisitor);
    }

}
