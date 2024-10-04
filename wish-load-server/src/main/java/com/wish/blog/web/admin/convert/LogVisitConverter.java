package com.wish.blog.web.admin.convert;

import com.wish.blog.entity.LogVisitDO;
import com.wish.blog.web.admin.vo.LogVisitVO;

/**
 * @author Yond
 */
public class LogVisitConverter {
    
    public static LogVisitVO do2vo(LogVisitDO from) {
        LogVisitVO to = new LogVisitVO();
        to.setId(from.getId());
        to.setUuid(from.getUuid());
        to.setUri(from.getUri());
        to.setMethod(from.getMethod());
        to.setParam(from.getParam());
        to.setBehavior(from.getBehavior());
        to.setContent(from.getContent());
        to.setRemark(from.getRemark());
        to.setIp(from.getIp());
        to.setIpSource(from.getIpSource());
        to.setOs(from.getOs());
        to.setBrowser(from.getBrowser());
        to.setDuration(from.getDuration());
        to.setCreateTime(from.getCreateTime());
        to.setUserAgent(from.getUserAgent());
        to.setStatus(from.getStatus());
        return to;
    }
    
}
