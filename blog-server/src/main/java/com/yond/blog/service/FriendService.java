package com.yond.blog.service;

import com.yond.blog.entity.FriendDO;
import com.yond.blog.web.blog.view.vo.FriendInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface FriendService {
    
    Pair<Integer, List<FriendDO>> page(Integer pageNo, Integer pageSize);
    
    List<com.yond.blog.web.blog.view.vo.Friend> getFriendVOList();
    
    void updateFriendPublishedById(Long friendId, Boolean published);
    
    void saveFriend(FriendDO friend);
    
    void updateFriend(com.yond.blog.web.blog.view.dto.Friend friend);
    
    void deleteFriend(Long id);
    
    void updateViewsByNickname(String nickname);
    
    FriendInfo getFriendInfo(boolean cache, boolean md);
    
    void updateFriendInfoContent(String content);
    
    void updateFriendInfoCommentEnabled(Boolean commentEnabled);
    
    void insertSelective(FriendDO friendDO);
    
    void updateSelective(FriendDO friendDO);
    
}
