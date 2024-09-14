package com.yond.blog.mapper;

import com.yond.blog.entity.FriendDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 友链持久层接口
 * @Author: Naccl
 * @Date: 2020-09-08
 */
@Mapper
@Repository
public interface FriendMapper {
    
    List<FriendDO> listAll();
    
    List<com.yond.blog.web.blog.view.vo.Friend> getFriendVOList();
    
    int updateFriendPublishedById(Long id, Boolean published);
    
    int saveFriend(FriendDO friend);
    
    int updateFriend(com.yond.blog.web.blog.view.dto.Friend friend);
    
    int deleteFriend(Long id);
    
    int updateViewsByNickname(String nickname);
    
    int insertSelective(FriendDO friendDO);
    
    int updateSelective(FriendDO friendDO);
}
