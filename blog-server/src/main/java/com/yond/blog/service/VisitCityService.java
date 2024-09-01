package com.yond.blog.service;

import com.yond.blog.entity.VisitCityDO;

import java.util.List;

public interface VisitCityService {

    List<VisitCityDO> listAll();

    void save(VisitCityDO cityVisitor);

}
