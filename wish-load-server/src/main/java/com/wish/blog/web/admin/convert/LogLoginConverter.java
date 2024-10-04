package com.wish.blog.web.admin.convert;

import com.wish.blog.entity.LogLoginDO;
import com.wish.blog.web.admin.vo.LogLoginVO;

/**
 * @Author: Yond
 */
public class LogLoginConverter {
    
    public static LogLoginVO do2vo(LogLoginDO from) {
        LogLoginVO to = new LogLoginVO();
        to.setId(from.getId());
        to.setUsername(from.getUsername());
        to.setIp(from.getIp());
        to.setIpSource(from.getIpSource());
        to.setOs(from.getOs());
        to.setBrowser(from.getBrowser());
        to.setLoginStatus(from.getLoginStatus());
        to.setDescription(from.getDescription());
        to.setCreateTime(from.getCreateTime());
        to.setUserAgent(from.getUserAgent());
        to.setStatus(from.getStatus());
        return to;
    }
    
}
