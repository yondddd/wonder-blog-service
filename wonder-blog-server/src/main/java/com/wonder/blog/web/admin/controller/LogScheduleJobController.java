package com.wonder.blog.web.admin.controller;

import com.wonder.blog.entity.LogScheduleJobDO;
import com.wonder.blog.service.LogScheduleJobService;
import com.wonder.blog.web.admin.req.LogScheduleJobPageReq;
import com.wonder.blog.web.admin.convert.LogScheduleJobConverter;
import com.wonder.blog.web.admin.req.LogScheduleJobDelReq;
import com.wonder.blog.web.admin.vo.LogScheduleJobVO;
import com.wonder.common.enums.EnableStatusEnum;
import com.wonder.common.resp.PageResponse;
import com.wonder.common.resp.Response;
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
        return PageResponse.<List<LogScheduleJobVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }

    @PostMapping("/del")
    public Response<Boolean> delete(@RequestBody LogScheduleJobDelReq req) {
        LogScheduleJobDO update = new LogScheduleJobDO();
        update.setId(req.getId());
        update.setStatus(EnableStatusEnum.DELETE.getVal());
        logScheduleJobService.updateSelective(update);
        return Response.success();
    }

}
