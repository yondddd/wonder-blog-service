package com.wish.blog.service;

import com.wish.blog.entity.ScheduleJobDO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ScheduleJobService {

    List<ScheduleJobDO> listJobs();

    void saveJob(ScheduleJobDO scheduleJob);

    void updateJob(ScheduleJobDO scheduleJob);

    void deleteJobById(Long jobId);

    void runJobById(Long jobId);

    void updateJobStatusById(Long jobId, Boolean status);

    ScheduleJobDO getJobById(Long jobId);

    Pair<Integer, List<ScheduleJobDO>> page(Integer pageNo, Integer pageSize);
}
