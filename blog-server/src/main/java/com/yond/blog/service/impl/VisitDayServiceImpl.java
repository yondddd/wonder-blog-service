package com.yond.blog.service.impl;

import com.yond.blog.entity.VisitRecordDO;
import com.yond.blog.mapper.VisitRecordMapper;
import com.yond.blog.service.VisitRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 访问记录业务层实现
 * @Author: Yond
 * @Date: 2021-02-23
 */
@Service
public class VisitDayServiceImpl implements VisitRecordService {

    private final VisitRecordMapper visitRecordMapper;

    public VisitDayServiceImpl(VisitRecordMapper visitRecordMapper) {
        this.visitRecordMapper = visitRecordMapper;
    }

    @Override
    public List<VisitRecordDO> listByLimit(Integer limit) {
        return visitRecordMapper.listByLimit(limit);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(VisitRecordDO visitRecord) {
        visitRecordMapper.insert(visitRecord);
    }
}
