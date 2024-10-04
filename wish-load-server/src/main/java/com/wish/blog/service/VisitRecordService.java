package com.wish.blog.service;

import com.wish.blog.entity.VisitRecordDO;

import java.util.List;

public interface VisitRecordService {

    List<VisitRecordDO> listByLimit(Integer limit);

    void insert(VisitRecordDO visitRecord);

}
