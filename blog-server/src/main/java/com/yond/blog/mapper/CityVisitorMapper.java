package com.yond.blog.mapper;

import com.yond.blog.entity.CityVisitorDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CityVisitorMapper {

    List<CityVisitorDO> listAll();

    int saveCityVisitor(CityVisitorDO cityVisitor);

}
