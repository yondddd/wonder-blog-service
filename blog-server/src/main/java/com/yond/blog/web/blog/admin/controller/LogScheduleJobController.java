package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.LogScheduleJobDO;
import com.yond.blog.service.LogScheduleJobService;
import com.yond.blog.web.blog.admin.convert.LogScheduleJobConverter;
import com.yond.blog.web.blog.admin.req.LogScheduleJobDelReq;
import com.yond.blog.web.blog.admin.req.LogScheduleJobPageReq;
import com.yond.blog.web.blog.admin.vo.LogScheduleJobVO;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/logJob")
public class LogScheduleJobController {
    
    @Resource
    private LogScheduleJobService logScheduleJobService;
    
    @PostMapping("/page")
    public PageResponse<List<LogScheduleJobVO>> page(@RequestBody LogScheduleJobPageReq req) {
        Pair<Integer, List<LogScheduleJobDO>> pair = logScheduleJobService.page(req.getStartDate(), req.getEndDate(), req.getPageNo(), req.getPageSize());
        List<LogScheduleJobVO> data = pair.getRight().stream().map(LogScheduleJobConverter::do2vo).toList();
        return PageResponse.<List<LogScheduleJobVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize());
    }
    
    @PostMapping("/del")
    public Response<Boolean> delete(@RequestBody LogScheduleJobDelReq req) {
        logScheduleJobService.deleteJobLogByLogId(logId);
        return Response.ok("删除成功");
    }
    
}
