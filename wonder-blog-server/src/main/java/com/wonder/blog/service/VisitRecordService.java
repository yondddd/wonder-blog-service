package com.wonder.blog.service;

import com.wonder.blog.entity.VisitRecordDO;

import java.util.List;

public interface VisitRecordService {

    List<VisitRecordDO> listByLimit(Integer limit);

    void insert(VisitRecordDO visitRecord);

}
