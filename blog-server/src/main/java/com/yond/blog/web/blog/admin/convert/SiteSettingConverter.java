package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.SiteSettingDO;
import com.yond.blog.web.blog.admin.vo.SiteSettingVO;

/**
 * @Description:
 * @Author: WangJieLong
 * @Date: 2024-08-30
 */
public class SiteSettingConverter {
    
    public static SiteSettingVO do2vo(SiteSettingDO from) {
        SiteSettingVO to = new SiteSettingVO();
        to.setId(from.getId());
        to.setNameEn(from.getNameEn());
        to.setNameZh(from.getNameZh());
        to.setValue(from.getValue());
        to.setType(from.getType());
        return to;
    }
    
    public static SiteSettingDO vo2do(SiteSettingVO from) {
        SiteSettingDO to = new SiteSettingDO();
        to.setId(from.getId());
        to.setNameEn(from.getNameEn());
        to.setNameZh(from.getNameZh());
        to.setValue(from.getValue());
        to.setType(from.getType());
        return to;
    }
    
    
}
