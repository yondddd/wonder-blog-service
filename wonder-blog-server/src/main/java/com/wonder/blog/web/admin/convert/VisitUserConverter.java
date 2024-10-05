package com.wonder.blog.web.admin.convert;

import com.wonder.blog.entity.VisitUserDO;
import com.wonder.blog.web.admin.vo.VisitUserVO;

/**
 * @author Yond
 */
public class VisitUserConverter {

    public static VisitUserVO do2vo(VisitUserDO from) {
        VisitUserVO to = new VisitUserVO();
        to.setId(from.getId());
        to.setUuid(from.getUuid());
        to.setIp(from.getIp());
        to.setIpSource(from.getIpSource());
        to.setOs(from.getOs());
        to.setBrowser(from.getBrowser());
        to.setCreateTime(from.getCreateTime());
        to.setLastTime(from.getLastTime());
        to.setPv(from.getPv());
        to.setUserAgent(from.getUserAgent());
        return to;
    }

}
