package com.yond.service;

import com.yond.entity.CityVisitorDO;

import java.util.List;

public interface CityVisitorService {

    List<CityVisitorDO> listAll();

    void save(CityVisitorDO cityVisitor);

}
