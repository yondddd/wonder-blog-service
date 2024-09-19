package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.ScheduleJobDO;
import com.yond.blog.service.ScheduleJobService;
import com.yond.blog.util.common.ValidatorUtils;
import com.yond.blog.web.blog.admin.convert.ScheduleJobConverter;
import com.yond.blog.web.blog.admin.req.ScheduleJobPageReq;
import com.yond.blog.web.blog.admin.vo.ScheduleJobVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description: 定时任务动态管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/job")
public class ScheduleJobController {
    
    @Resource
    private ScheduleJobService scheduleJobService;
    
    @PostMapping("/page")
    public PageResponse<List<ScheduleJobVO>> page(@RequestBody ScheduleJobPageReq req) {
        Pair<Integer, List<ScheduleJobDO>> pair = scheduleJobService.page(req.getPageNo(), req.getPageSize());
        List<ScheduleJobVO> data = pair.getRight().stream().map(ScheduleJobConverter::do2vo).toList();
        return PageResponse.<List<ScheduleJobVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize());
    }
    
    
    @OperationLogger("新建定时任务")
    @PostMapping("/add")
    public Response saveJob(@RequestBody ScheduleJobDO scheduleJob) {
        scheduleJob.setStatus(false);
        scheduleJob.setCreateTime(new Date());
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.saveJob(scheduleJob);
        return Response.ok("添加成功");
    }
    
    
    @OperationLogger("修改定时任务")
    @PostMapping("/edit")
    public Response updateJob(@RequestBody ScheduleJobDO scheduleJob) {
        scheduleJob.setStatus(false);
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.updateJob(scheduleJob);
        return Response.ok("修改成功");
    }
    
    
    @OperationLogger("删除定时任务")
    @PostMapping("/del")
    public Response deleteJob(@RequestParam Long jobId) {
        scheduleJobService.deleteJobById(jobId);
        return Response.ok("删除成功");
    }
    
    
    @OperationLogger("立即执行定时任务")
    @PostMapping("/run")
    public Response runJob(@RequestParam Long jobId) {
        scheduleJobService.runJobById(jobId);
        return Response.ok("提交执行");
    }
    
    
    @OperationLogger("更新任务状态")
    @PostMapping("/updateStatus")
    public Response updateJobStatus(@RequestParam Long jobId, @RequestParam Boolean status) {
        scheduleJobService.updateJobStatusById(jobId, status);
        return Response.ok("更新成功");
    }
    
    
}
