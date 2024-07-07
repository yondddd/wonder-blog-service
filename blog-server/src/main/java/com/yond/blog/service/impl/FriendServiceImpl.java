package com.yond.blog.service.impl;

import com.yond.blog.cache.local.FriendCache;
import com.yond.common.exception.PersistenceException;
import com.yond.blog.entity.FriendDO;
import com.yond.blog.entity.SiteSettingDO;
import com.yond.blog.mapper.FriendMapper;
import com.yond.blog.web.blog.view.vo.FriendInfo;
import com.yond.blog.service.FriendService;
import com.yond.blog.service.SiteSettingService;
import com.yond.blog.util.markdown.MarkdownUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 友链业务层实现
 * @Author: Naccl
 * @Date: 2020-09-08
 */
@Service
public class FriendServiceImpl implements FriendService {

    private final FriendMapper friendMapper;
    private final SiteSettingService siteSettingService;

    public FriendServiceImpl(FriendMapper friendMapper, SiteSettingService siteSettingService) {
        this.friendMapper = friendMapper;
        this.siteSettingService = siteSettingService;
    }

    @Override
    public List<FriendDO> getFriendList() {
        return friendMapper.getFriendList();
    }

    @Override
    public List<com.yond.blog.web.blog.view.vo.Friend> getFriendVOList() {
        return friendMapper.getFriendVOList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateFriendPublishedById(Long friendId, Boolean published) {
        if (friendMapper.updateFriendPublishedById(friendId, published) != 1) {
            throw new PersistenceException("操作失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveFriend(FriendDO friend) {
        friend.setViews(0);
        friend.setCreateTime(new Date());
        if (friendMapper.saveFriend(friend) != 1) {
            throw new PersistenceException("添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateFriend(com.yond.blog.web.blog.view.dto.Friend friend) {
        if (friendMapper.updateFriend(friend) != 1) {
            throw new PersistenceException("修改失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFriend(Long id) {
        if (friendMapper.deleteFriend(id) != 1) {
            throw new PersistenceException("删除失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateViewsByNickname(String nickname) {
        if (friendMapper.updateViewsByNickname(nickname) != 1) {
            throw new PersistenceException("操作失败");
        }
    }

    @Override
    public FriendInfo getFriendInfo(boolean cache, boolean md) {
        if (cache) {
            FriendInfo friendInfo = FriendCache.get();
            if (friendInfo != null) {
                return friendInfo;
            }
        }
        List<SiteSettingDO> siteSettings = siteSettingService.getFriendInfo();
        FriendInfo friendInfo = new FriendInfo();
        for (SiteSettingDO siteSetting : siteSettings) {
            if ("friendContent".equals(siteSetting.getNameEn())) {
                if (md) {
                    friendInfo.setContent(MarkdownUtils.markdownToHtmlExtensions(siteSetting.getValue()));
                } else {
                    friendInfo.setContent(siteSetting.getValue());
                }
            } else if ("friendCommentEnabled".equals(siteSetting.getNameEn())) {
                friendInfo.setCommentEnabled("1".equals(siteSetting.getValue()));
            }
        }
        if (cache && md) {
            FriendCache.set(friendInfo);
        }
        return friendInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateFriendInfoContent(String content) {
        if (siteSettingService.updateFriendInfoContent(content) != 1) {
            throw new PersistenceException("修改失败");
        }
        deleteFriendInfoRedisCache();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateFriendInfoCommentEnabled(Boolean commentEnabled) {
        if (siteSettingService.updateFriendInfoCommentEnabled(commentEnabled) != 1) {
            throw new PersistenceException("修改失败");
        }
        deleteFriendInfoRedisCache();
    }

    /**
     * 删除友链页面缓存
     */
    private void deleteFriendInfoRedisCache() {
        FriendCache.del();
    }

}
