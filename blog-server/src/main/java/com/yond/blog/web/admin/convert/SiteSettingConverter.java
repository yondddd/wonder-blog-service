package com.yond.blog.web.admin.convert;

import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.web.admin.vo.SiteSettingVO;

/**
 * @Description:
 * @Author: yond
 */
public class SiteSettingConverter {

    public static SiteSettingVO do2vo(SiteConfigDO from) {
        SiteSettingVO to = new SiteSettingVO();
        to.setId(from.getId());
        to.setNameEn(from.getKey());
        to.setNameZh(from.getName());
        to.setValue(from.getValue());
        to.setType(from.getType());
        return to;
    }

    public static SiteConfigDO vo2do(SiteSettingVO from) {
        SiteConfigDO to = new SiteConfigDO();
        to.setId(from.getId());
        to.setKey(from.getNameEn());
        to.setName(from.getNameZh());
        to.setValue(from.getValue());
        to.setType(from.getType());
        return to;
    }


}
