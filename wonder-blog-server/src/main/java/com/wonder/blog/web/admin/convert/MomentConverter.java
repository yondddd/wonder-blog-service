package com.wonder.blog.web.admin.convert;

import com.wonder.blog.entity.MomentDO;
import com.wonder.blog.web.admin.vo.MomentVO;

/**
 * @Author Yond
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
