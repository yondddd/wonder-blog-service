package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.MomentDO;
import com.yond.blog.web.blog.admin.vo.MomentVO;

/**
 * @author yond
 * @date 8/31/2024
 * @description
 */
public class MomentConverter {


    public static MomentVO do2vo(MomentDO momentDO) {
        MomentVO momentVO = new MomentVO();
        momentVO.setId(momentDO.getId());
        momentVO.setContent(momentDO.getContent());
        momentVO.setLikes(momentDO.getLikes());
        momentVO.setPublished(momentDO.getPublished());
        momentVO.setCreateTime(momentDO.getCreateTime());
        return momentVO;
    }


    public static MomentDO vo2do(MomentVO from) {
        MomentDO momentDO = new MomentDO();
        momentDO.setId(from.getId());
        momentDO.setContent(from.getContent());
        momentDO.setLikes(from.getLikes());
        momentDO.setPublished(from.getPublished());
        momentDO.setCreateTime(from.getCreateTime());
        return momentDO;
    }

}