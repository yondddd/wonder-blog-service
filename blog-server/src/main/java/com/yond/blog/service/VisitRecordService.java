package com.yond.blog.service;

import com.yond.blog.entity.VisitRecordDO;

import java.util.List;

public interface VisitRecordService {

    List<VisitRecordDO> listByLimit(Integer limit);

    void insert(VisitRecordDO visitRecord);

}
