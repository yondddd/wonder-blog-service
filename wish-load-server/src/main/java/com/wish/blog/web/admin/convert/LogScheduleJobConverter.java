package com.wish.blog.web.admin.convert;

import com.wish.blog.entity.LogScheduleJobDO;
import com.wish.blog.web.admin.vo.LogScheduleJobVO;

/**
 * @Author: Yond
 */
public class LogScheduleJobConverter {
    
    public static LogScheduleJobVO do2vo(LogScheduleJobDO from) {
        LogScheduleJobVO to = new LogScheduleJobVO();
        to.setId(from.getId());
        to.setJobId(from.getJobId());
        to.setBeanName(from.getBeanName());
        to.setMethodName(from.getMethodName());
        to.setParams(from.getParams());
        to.setRunStatus(from.getRunStatus());
        to.setError(from.getError());
        to.setDuration(from.getDuration());
        to.setCreateTime(from.getCreateTime());
        to.setStatus(from.getStatus());
        return to;
    }
    
}
