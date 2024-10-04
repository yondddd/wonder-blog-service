package com.wish.blog.service;

import com.wish.blog.entity.VisitCityDO;

import java.util.List;

public interface VisitCityService {

    List<VisitCityDO> listAll();

    void save(VisitCityDO cityVisitor);

}
