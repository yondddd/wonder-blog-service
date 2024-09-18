package com.yond.blog.mapper;

import com.yond.blog.entity.VisitRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 访问记录持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface VisitRecordMapper {

    List<VisitRecordDO> listByLimit(Integer limit);

    int insert(VisitRecordDO visitRecord);
}
