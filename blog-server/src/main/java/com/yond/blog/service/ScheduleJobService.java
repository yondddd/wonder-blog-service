package com.yond.blog.service;

import com.yond.blog.entity.ScheduleJobDO;
import com.yond.blog.entity.ScheduleJobLogDO;

import java.util.List;

public interface ScheduleJobService {

    List<ScheduleJobDO> getJobList();

    void saveJob(ScheduleJobDO scheduleJob);

    void updateJob(ScheduleJobDO scheduleJob);

    void deleteJobById(Long jobId);

    void runJobById(Long jobId);

    void updateJobStatusById(Long jobId, Boolean status);

    List<ScheduleJobLogDO> getJobLogListByDate(String startDate, String endDate);

    void saveJobLog(ScheduleJobLogDO log);

    void deleteJobLogByLogId(Long logId);

    ScheduleJobDO getJobById(Long jobId);
}
