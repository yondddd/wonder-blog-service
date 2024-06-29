package com.yond.blog.service;

import com.yond.blog.entity.Friend;
import com.yond.blog.model.vo.FriendInfo;

import java.util.List;

public interface FriendService {
    List<Friend> getFriendList();

    List<com.yond.blog.model.vo.Friend> getFriendVOList();

    void updateFriendPublishedById(Long friendId, Boolean published);

    void saveFriend(Friend friend);

    void updateFriend(com.yond.blog.model.dto.Friend friend);

    void deleteFriend(Long id);

    void updateViewsByNickname(String nickname);

    FriendInfo getFriendInfo(boolean cache, boolean md);

    void updateFriendInfoContent(String content);

    void updateFriendInfoCommentEnabled(Boolean commentEnabled);
}
