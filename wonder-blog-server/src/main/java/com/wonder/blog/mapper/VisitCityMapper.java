package com.wonder.blog.mapper;

import com.wonder.blog.entity.VisitCityDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VisitCityMapper {

    List<VisitCityDO> listAll();

    int saveCityVisitor(VisitCityDO cityVisitor);

}
