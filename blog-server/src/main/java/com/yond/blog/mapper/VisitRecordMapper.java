package com.yond.blog.mapper;

import com.yond.blog.entity.VisitRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 访问记录持久层接口
 * @Author: Naccl
 * @Date: 2021-02-23
 */
@Mapper
@Repository
public interface VisitRecordMapper {

    List<VisitRecord> listByLimit(Integer limit);

    int insert(VisitRecord visitRecord);
}
