package com.wish.blog.web.admin.controller;

import com.wish.blog.entity.LogExceptionDO;
import com.wish.blog.service.LogExceptionService;
import com.wish.blog.web.admin.convert.LogExceptionConverter;
import com.wish.blog.web.admin.req.LogExceptionDelReq;
import com.wish.blog.web.admin.req.LogExceptionPageReq;
import com.wish.blog.web.admin.vo.LogExceptionVO;
import com.wish.common.enums.EnableStatusEnum;
import com.wish.common.resp.PageResponse;
import com.wish.common.resp.Response;
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
