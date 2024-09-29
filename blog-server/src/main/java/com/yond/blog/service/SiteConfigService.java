package com.yond.blog.service;

import com.yond.blog.entity.SiteConfigDO;
import com.yond.common.enums.SiteConfigTypeEnum;

import java.util.List;

public interface SiteConfigService {

    String getValue(String key);

    void updateValue(String key, String value);

    List<SiteConfigDO> listByType(SiteConfigTypeEnum typeEnum);

    List<SiteConfigDO> listAll();

    /**
     * 全量覆盖更新
     * id不存在的新增、id存在的更新
     * nameEn 不能重复
     */
    void coverUpdate(List<SiteConfigDO> data);

}
