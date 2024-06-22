package com.yond.service.impl;

import com.yond.entity.VisitRecord;
import com.yond.mapper.VisitRecordMapper;
import com.yond.service.VisitRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 访问记录业务层实现
 * @Author: Naccl
 * @Date: 2021-02-23
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordMapper visitRecordMapper;

    public VisitRecordServiceImpl(VisitRecordMapper visitRecordMapper) {
        this.visitRecordMapper = visitRecordMapper;
    }

    @Override
    public List<VisitRecord> listByLimit(Integer limit) {
        return visitRecordMapper.listByLimit(limit);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(VisitRecord visitRecord) {
        visitRecordMapper.insert(visitRecord);
    }
}
