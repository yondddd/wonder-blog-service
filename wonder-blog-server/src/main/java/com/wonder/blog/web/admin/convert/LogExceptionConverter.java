package com.wonder.blog.web.admin.convert;

import com.wonder.blog.entity.LogExceptionDO;
import com.wonder.blog.web.admin.vo.LogExceptionVO;

/**
 * @Author: Yond
 */
public class LogExceptionConverter {
    
    public static LogExceptionVO do2vo(LogExceptionDO from) {
        LogExceptionVO to = new LogExceptionVO();
        to.setId(from.getId());
        to.setUri(from.getUri());
        to.setMethod(from.getMethod());
        to.setParam(from.getParam());
        to.setDescription(from.getDescription());
        to.setError(from.getError());
        to.setIp(from.getIp());
        to.setIpSource(from.getIpSource());
        to.setOs(from.getOs());
        to.setBrowser(from.getBrowser());
        to.setCreateTime(from.getCreateTime());
        to.setUserAgent(from.getUserAgent());
        to.setStatus(from.getStatus());
        return to;
    }
    
}
