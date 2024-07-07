package com.yond.blog.service;

import com.yond.blog.entity.FriendDO;
import com.yond.blog.web.blog.view.vo.FriendInfo;

import java.util.List;

public interface FriendService {
    List<FriendDO> getFriendList();

    List<com.yond.blog.web.blog.view.vo.Friend> getFriendVOList();

    void updateFriendPublishedById(Long friendId, Boolean published);

    void saveFriend(FriendDO friend);

    void updateFriend(com.yond.blog.web.blog.view.dto.Friend friend);

    void deleteFriend(Long id);

    void updateViewsByNickname(String nickname);

    FriendInfo getFriendInfo(boolean cache, boolean md);

    void updateFriendInfoContent(String content);

    void updateFriendInfoCommentEnabled(Boolean commentEnabled);
}
