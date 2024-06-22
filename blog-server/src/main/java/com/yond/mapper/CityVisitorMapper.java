package com.yond.mapper;

import com.yond.entity.CityVisitorDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 城市访客数量统计持久层接口
 * @Author: Naccl
 * @Date: 2021-02-26
 */
@Mapper
@Repository
public interface CityVisitorMapper {

    List<CityVisitorDO> listAll();

    int saveCityVisitor(CityVisitorDO cityVisitor);

}
