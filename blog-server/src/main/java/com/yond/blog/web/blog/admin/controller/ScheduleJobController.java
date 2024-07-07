package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.ScheduleJobDO;
import com.yond.blog.entity.ScheduleJobLogDO;
import com.yond.blog.service.ScheduleJobService;
import com.yond.blog.util.common.ValidatorUtils;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description: 定时任务动态管理
 * @Author: Naccl
 * @Date: 2020-11-01
 */
@RestController
@RequestMapping("/admin")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 分页查询定时任务列表
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/jobs")
    public Response jobs(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ScheduleJobDO> pageInfo = new PageInfo<>(scheduleJobService.getJobList());
        return Response.ok("请求成功", pageInfo);
    }

    /**
     * 新建定时任务
     *
     * @param scheduleJob
     * @return
     */
    @OperationLogger("新建定时任务")
    @PostMapping("/job")
    public Response saveJob(@RequestBody ScheduleJobDO scheduleJob) {
        scheduleJob.setStatus(false);
        scheduleJob.setCreateTime(new Date());
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.saveJob(scheduleJob);
        return Response.ok("添加成功");
    }

    /**
     * 修改定时任务
     *
     * @param scheduleJob
     * @return
     */
    @OperationLogger("修改定时任务")
    @PutMapping("/job")
    public Response updateJob(@RequestBody ScheduleJobDO scheduleJob) {
        scheduleJob.setStatus(false);
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.updateJob(scheduleJob);
        return Response.ok("修改成功");
    }

    /**
     * 删除定时任务
     *
     * @param jobId 任务id
     * @return
     */
    @OperationLogger("删除定时任务")
    @DeleteMapping("/job")
    public Response deleteJob(@RequestParam Long jobId) {
        scheduleJobService.deleteJobById(jobId);
        return Response.ok("删除成功");
    }

    /**
     * 立即执行任务
     *
     * @param jobId 任务id
     * @return
     */
    @OperationLogger("立即执行定时任务")
    @PostMapping("/job/run")
    public Response runJob(@RequestParam Long jobId) {
        scheduleJobService.runJobById(jobId);
        return Response.ok("提交执行");
    }

    /**
     * 更新任务状态：暂停或恢复
     *
     * @param jobId  任务id
     * @param status 状态
     * @return
     */
    @OperationLogger("更新任务状态")
    @PutMapping("/job/status")
    public Response updateJobStatus(@RequestParam Long jobId, @RequestParam Boolean status) {
        scheduleJobService.updateJobStatusById(jobId, status);
        return Response.ok("更新成功");
    }

    /**
     * 分页查询定时任务日志列表
     *
     * @param date     按执行时间查询
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/job/logs")
    public Response logs(@RequestParam(defaultValue = "") String[] date,
                         @RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize) {
        String startDate = null;
        String endDate = null;
        if (date.length == 2) {
            startDate = date[0];
            endDate = date[1];
        }
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<ScheduleJobLogDO> pageInfo = new PageInfo<>(scheduleJobService.getJobLogListByDate(startDate, endDate));
        return Response.ok("请求成功", pageInfo);
    }

    /**
     * 按id删除任务日志
     *
     * @param logId 日志id
     * @return
     */
    @DeleteMapping("/job/log")
    public Response delete(@RequestParam Long logId) {
        scheduleJobService.deleteJobLogByLogId(logId);
        return Response.ok("删除成功");
    }
}
