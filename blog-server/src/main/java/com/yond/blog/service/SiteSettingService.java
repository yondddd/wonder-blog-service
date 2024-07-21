package com.yond.blog.service;

import com.yond.blog.entity.SiteSettingDO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SiteSettingService {
    Map<String, List<SiteSettingDO>> getList();

    Map<String, Object> getSiteInfo();

    String getWebTitleSuffix();

    void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds);

    List<SiteSettingDO> getFriendInfo();

    int updateFriendInfoContent(String content);

    int updateFriendInfoCommentEnabled(Boolean commentEnabled);

    List<SiteSettingDO> listAll();
}
