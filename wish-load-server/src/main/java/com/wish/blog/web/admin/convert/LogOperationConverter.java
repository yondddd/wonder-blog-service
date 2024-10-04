package com.wish.blog.web.admin.convert;

import com.wish.blog.entity.LogOperationDO;
import com.wish.blog.web.admin.vo.LogOperationVO;

/**
 * @Author: Yond
 */
public class LogOperationConverter {

    public static LogOperationVO do2vo(LogOperationDO from) {
        LogOperationVO to = new LogOperationVO();
        to.setId(from.getId());
        to.setUsername(from.getUsername());
        to.setUri(from.getUri());
        to.setMethod(from.getMethod());
        to.setParam(from.getParam());
        to.setDescription(from.getDescription());
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
