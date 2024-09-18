package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.web.blog.admin.vo.SiteSettingVO;

/**
 * @Description:
 * @Author: yond
 */
public class SiteSettingConverter {

    public static SiteSettingVO do2vo(SiteConfigDO from) {
        SiteSettingVO to = new SiteSettingVO();
        to.setId(from.getId());
        to.setNameEn(from.getNameEn());
        to.setNameZh(from.getNameZh());
        to.setValue(from.getValue());
        to.setType(from.getType());
        return to;
    }

    public static SiteConfigDO vo2do(SiteSettingVO from) {
        SiteConfigDO to = new SiteConfigDO();
        to.setId(from.getId());
        to.setNameEn(from.getNameEn());
        to.setNameZh(from.getNameZh());
        to.setValue(from.getValue());
        to.setType(from.getType());
        return to;
    }


}
