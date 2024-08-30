package com.yond.blog.service;

import com.yond.blog.entity.SiteSettingDO;
import com.yond.common.enums.SiteSettingTypeEnum;

import java.util.List;
import java.util.Map;

public interface SiteSettingService {
    
    @Deprecated
    Map<String, Object> getSiteInfoForView();
    
    String getValue(String key);
    
    void updateValue(String key, String value);
    
    List<SiteSettingDO> listByType(SiteSettingTypeEnum typeEnum);
    
    List<SiteSettingDO> listAll();
    
    /**
     * 全量覆盖更新
     * id不存在的新增、id存在的更新
     * nameEn 不能重复
     */
    void coverUpdate(List<SiteSettingDO> data);
    
}
