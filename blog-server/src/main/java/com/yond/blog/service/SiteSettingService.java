package com.yond.blog.service;

import com.yond.blog.entity.SiteSettingDO;
import com.yond.common.enums.SiteSettingTypeEnum;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SiteSettingService {

    @Deprecated
    Map<String, List<SiteSettingDO>> getListForAdmin();

    @Deprecated
    Map<String, Object> getSiteInfoForView();

    String getValue(String key);

    void updateValue(String key, String value);

    @Deprecated
    void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds);

    List<SiteSettingDO> listByType(SiteSettingTypeEnum typeEnum);

    List<SiteSettingDO> listAll();

}
