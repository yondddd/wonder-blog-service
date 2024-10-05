package com.wonder.blog.web.admin.convert;

import com.wonder.blog.entity.ScheduleJobDO;
import com.wonder.blog.web.admin.vo.ScheduleJobVO;

/**
 * @Author: Yond
 */
public class ScheduleJobConverter {
    
    public static ScheduleJobVO do2vo(ScheduleJobDO from) {
        ScheduleJobVO to = new ScheduleJobVO();
        to.setId(from.getId());
        to.setBeanName(from.getBeanName());
        to.setMethodName(from.getMethodName());
        to.setParams(from.getParams());
        to.setCron(from.getCron());
        to.setRunStatus(from.getRunStatus());
        to.setStatus(from.getStatus());
        to.setRemark(from.getRemark());
        to.setCreateTime(from.getCreateTime());
        return to;
    }
    
}
