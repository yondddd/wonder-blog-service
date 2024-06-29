package com.yond.blog.service;

import com.yond.blog.entity.VisitRecord;

import java.util.List;

public interface VisitRecordService {

    List<VisitRecord> listByLimit(Integer limit);

    void insert(VisitRecord visitRecord);

}
