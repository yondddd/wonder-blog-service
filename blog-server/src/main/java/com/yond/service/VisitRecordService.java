package com.yond.service;

import com.yond.entity.VisitRecord;

import java.util.List;

public interface VisitRecordService {

    List<VisitRecord> listByLimit(Integer limit);

    void insert(VisitRecord visitRecord);

}
