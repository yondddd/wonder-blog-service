package com.wonder.blog.web.admin.controller;

import com.wonder.blog.entity.LogExceptionDO;
import com.wonder.blog.service.LogExceptionService;
import com.wonder.blog.web.admin.convert.LogExceptionConverter;
import com.wonder.blog.web.admin.req.LogExceptionDelReq;
import com.wonder.blog.web.admin.req.LogExceptionPageReq;
import com.wonder.blog.web.admin.vo.LogExceptionVO;
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
 * @Description: 异常日志后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/logException")
public class LogExceptionController {

    @Resource
    private LogExceptionService logExceptionService;

    @PostMapping("/page")
    public PageResponse<List<LogExceptionVO>> exceptionLogs(@RequestBody LogExceptionPageReq req) {
        Pair<Integer, List<LogExceptionDO>> pair = logExceptionService.page(req.getStartDate(), req.getEndDate(), req.getPageNo(), req.getPageSize());
        List<LogExceptionVO> data = pair.getRight().stream().map(LogExceptionConverter::do2vo).toList();
        return PageResponse.<List<LogExceptionVO>>custom().setTotal(pair.getLeft()).setData(data).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }

    @PostMapping("/del")
    public Response<Boolean> del(@RequestBody LogExceptionDelReq req) {
        LogExceptionDO update = new LogExceptionDO();
        update.setId(req.getId());
        update.setStatus(EnableStatusEnum.DELETE.getVal());
        logExceptionService.updateSelective(update);
        return Response.success();
    }

}
