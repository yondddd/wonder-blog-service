package com.wonder.blog.service;

import com.wonder.blog.entity.VisitCityDO;

import java.util.List;

public interface VisitCityService {

    List<VisitCityDO> listAll();

    void save(VisitCityDO cityVisitor);

}
