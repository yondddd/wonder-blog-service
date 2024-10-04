package com.wish.blog.service;

import com.wish.blog.entity.SiteConfigDO;
import com.wish.common.enums.SiteConfigTypeEnum;

import java.util.List;

public interface SiteConfigService {

    String getValue(String key);

    void updateValue(String key, String value);

    List<SiteConfigDO> listByType(SiteConfigTypeEnum typeEnum);

    List<SiteConfigDO> listAll();

    /**
     * 全量覆盖更新
     * key不存在的新增、存在的更新
     */
    void coverUpdate(List<SiteConfigDO> data);

}
