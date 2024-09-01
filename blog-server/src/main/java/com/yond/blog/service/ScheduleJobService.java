package com.yond.blog.service;

import com.yond.blog.entity.LogScheduleJobDO;
import com.yond.blog.entity.ScheduleJobDO;

import java.util.List;

public interface ScheduleJobService {

    List<ScheduleJobDO> getJobList();

    void saveJob(ScheduleJobDO scheduleJob);

    void updateJob(ScheduleJobDO scheduleJob);

    void deleteJobById(Long jobId);

    void runJobById(Long jobId);

    void updateJobStatusById(Long jobId, Boolean status);

    List<LogScheduleJobDO> getJobLogListByDate(String startDate, String endDate);

    void saveJobLog(LogScheduleJobDO log);

    void deleteJobLogByLogId(Long logId);

    ScheduleJobDO getJobById(Long jobId);
}
