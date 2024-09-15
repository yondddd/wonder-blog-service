package com.yond.blog.service.impl;

import com.yond.blog.cache.local.FriendCache;
import com.yond.blog.entity.FriendDO;
import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.mapper.FriendMapper;
import com.yond.blog.service.FriendService;
import com.yond.blog.service.SiteConfigService;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.blog.web.blog.view.vo.FriendInfo;
import com.yond.common.constant.SiteSettingConstant;
import com.yond.common.enums.SiteSettingTypeEnum;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * @Description: 友链业务层实现
 * @Author: Yond
 * @Date: 2020-09-08
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendMapper friendMapper;
    @Resource
    private SiteConfigService siteConfigService;

    private List<FriendDO> listAll() {
        List<FriendDO> cache = FriendCache.getAll();
        if (cache != null) {
            return cache;
        }
        cache = friendMapper.listAll();
        return cache;
    }

    @Override
    public Pair<Integer, List<FriendDO>> page(Integer pageNo, Integer pageSize) {
        List<FriendDO> all = this.listAll();
        all.sort(Comparator.comparing(FriendDO::getId).reversed());
        return Pair.of(all.size(), PageUtil.pageList(all, pageNo, pageSize));
    }

    @Override
    public List<com.yond.blog.web.blog.view.vo.Friend> getFriendVOList() {
        return friendMapper.getFriendVOList();
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
        List<SiteConfigDO> siteSettings = siteConfigService.listAll()
                .stream().filter(x -> SiteSettingTypeEnum.FRIEND.getVal().equals(x.getType())).toList();
        FriendInfo friendInfo = new FriendInfo();
        for (SiteConfigDO siteSetting : siteSettings) {
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
        siteConfigService.updateValue(SiteSettingConstant.FRIEND_CONTENT, content);
        deleteFriendInfoRedisCache();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateFriendInfoCommentEnabled(Boolean commentEnabled) {
        siteConfigService.updateValue(SiteSettingConstant.FRIEND_COMMENT_ENABLED, commentEnabled.toString());
        deleteFriendInfoRedisCache();
    }

    @Override
    public void insertSelective(FriendDO friendDO) {
        friendMapper.insertSelective(friendDO);
    }

    @Override
    public void updateSelective(FriendDO friendDO) {
        friendMapper.updateSelective(friendDO);
    }

    /**
     * 删除友链页面缓存
     */
    private void deleteFriendInfoRedisCache() {
        FriendCache.delAll();
    }

}
