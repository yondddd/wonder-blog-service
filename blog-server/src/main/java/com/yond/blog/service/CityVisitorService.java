package com.yond.blog.service;

import com.yond.blog.entity.CityVisitorDO;

import java.util.List;

public interface CityVisitorService {

    List<CityVisitorDO> listAll();

    void save(CityVisitorDO cityVisitor);

}
