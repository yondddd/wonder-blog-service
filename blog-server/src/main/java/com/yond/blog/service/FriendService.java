package com.yond.blog.service;

import com.yond.blog.entity.FriendDO;
import com.yond.blog.web.blog.admin.vo.FriendConfigVO;
import com.yond.blog.web.blog.view.vo.FriendInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface FriendService {

    Pair<Integer, List<FriendDO>> page(Integer pageNo, Integer pageSize);

    List<com.yond.blog.web.blog.view.vo.Friend> getFriendVOList();

    void updateViewsByNickname(String nickname);

    FriendInfo getFriendInfo(boolean cache, boolean md);

    void updateFriendInfoContent(String content);

    void updateFriendInfoCommentEnabled(Boolean commentEnabled);

    void insertSelective(FriendDO friendDO);

    void updateSelective(FriendDO friendDO);

    FriendConfigVO friendConfig();
}
