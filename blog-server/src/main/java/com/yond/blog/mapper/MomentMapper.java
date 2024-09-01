package com.yond.blog.mapper;

import com.yond.blog.entity.MomentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客动态持久层接口
 * @Author: Naccl
 * @Date: 2020-08-24
 */
@Mapper
@Repository
public interface MomentMapper {

    List<MomentDO> listByStatus(@Param("status") Integer status);

    int incrLikeById(Long momentId);

    int insertSelective(MomentDO moment);

    int updateSelective(MomentDO moment);

}
