package com.yond.blog.mapper;

import com.yond.blog.entity.FriendDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 友链持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface FriendMapper {
    
    List<FriendDO> listAll();
    
    int incrViewById(Long id);
    
    int insertSelective(FriendDO friendDO);
    
    int updateSelective(FriendDO friendDO);
    
}
