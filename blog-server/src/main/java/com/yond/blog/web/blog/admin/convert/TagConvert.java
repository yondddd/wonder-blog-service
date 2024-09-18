package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.TagDO;
import com.yond.blog.web.blog.admin.vo.TagVO;

/**
 * @Author Yond
 */
public class TagConvert {

    public static TagVO do2vo(TagDO from) {
        TagVO tagVO = new TagVO();
        tagVO.setId(from.getId());
        tagVO.setName(from.getName());
        tagVO.setColor(from.getColor());
        return tagVO;
    }

    public static TagDO vo2do(TagVO from) {
        TagDO tagDO = new TagDO();
        tagDO.setId(from.getId());
        tagDO.setName(from.getName());
        tagDO.setColor(from.getColor());
        return tagDO;
    }

}
