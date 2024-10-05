package com.wonder.blog.web.admin.controller;

import com.wonder.blog.entity.ScheduleJobDO;
import com.wonder.blog.service.ScheduleJobService;
import com.wonder.blog.web.admin.convert.ScheduleJobConverter;
import com.wonder.blog.web.admin.req.*;
import com.wonder.blog.web.admin.vo.ScheduleJobVO;
import com.wonder.common.annotation.OperationLogger;
import com.wonder.common.enums.EnableStatusEnum;
import com.wonder.common.resp.PageResponse;
import com.wonder.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return PageResponse.<List<ScheduleJobVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }
    
    @OperationLogger("新建定时任务")
    @PostMapping("/add")
    public Response<Boolean> saveJob(@RequestBody ScheduleJobAddReq req) {
        ScheduleJobDO job = ScheduleJobDO.custom()
                .setBeanName(req.getBeanName())
                .setMethodName(req.getMethodName())
                .setParams(req.getParams())
                .setCron(req.getCron())
                .setRemark(req.getRemark())
                .setRunStatus(false)
                .setStatus(EnableStatusEnum.ENABLE.getVal())
                .setCreateTime(new Date());
        scheduleJobService.saveJob(job);
        return Response.success();
    }
    
    @OperationLogger("修改定时任务")
    @PostMapping("/edit")
    public Response<Boolean> updateJob(@RequestBody ScheduleJobEditReq req) {
        ScheduleJobDO job = ScheduleJobDO.custom()
                .setId(req.getId())
                .setBeanName(req.getBeanName())
                .setMethodName(req.getMethodName())
                .setParams(req.getParams())
                .setCron(req.getCron())
                .setRemark(req.getRemark())
                .setRunStatus(false)
                .setStatus(EnableStatusEnum.ENABLE.getVal())
                .setCreateTime(new Date());
        scheduleJobService.updateJob(job);
        return Response.success();
    }
    
    @OperationLogger("删除定时任务")
    @PostMapping("/del")
    public Response<Boolean> deleteJob(@RequestBody ScheduleJobIdReq req) {
        scheduleJobService.deleteJobById(req.getId());
        return Response.success();
    }
    
    @OperationLogger("立即执行定时任务")
    @PostMapping("/run")
    public Response<Boolean> runJob(@RequestBody ScheduleJobIdReq req) {
        scheduleJobService.runJobById(req.getId());
        return Response.success();
    }
    
    @OperationLogger("更新任务状态")
    @PostMapping("/updateStatus")
    public Response<Boolean> updateJobStatus(@RequestBody ScheduleJobStatusReq req) {
        scheduleJobService.updateJobStatusById(req.getId(), req.getStatus());
        return Response.success();
    }
    
}
