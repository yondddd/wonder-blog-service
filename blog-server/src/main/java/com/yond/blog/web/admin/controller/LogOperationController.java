package com.yond.blog.web.admin.controller;

import com.yond.blog.entity.LogOperationDO;
import com.yond.blog.service.LogOperationService;
import com.yond.blog.web.admin.convert.LogOperationConverter;
import com.yond.blog.web.admin.req.LogOperationDelReq;
import com.yond.blog.web.admin.req.LogOperationPageReq;
import com.yond.blog.web.admin.vo.LogOperationVO;
import com.yond.common.enums.EnableStatusEnum;
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
 * @Description: 操作日志后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/logOperation")
public class LogOperationController {

    @Resource
    private LogOperationService logOperationService;

    @PostMapping("/page")
    public PageResponse<List<LogOperationVO>> page(@RequestBody LogOperationPageReq req) {
        Pair<Integer, List<LogOperationDO>> pair = logOperationService.page(req.getStartDate(), req.getEndDate(), req.getPageNo(), req.getPageSize());
        List<LogOperationVO> data = pair.getRight().stream().map(LogOperationConverter::do2vo).toList();
        return PageResponse.<List<LogOperationVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }

    @PostMapping("/del")
    public Response<Boolean> delete(@RequestBody LogOperationDelReq req) {
        LogOperationDO update = new LogOperationDO();
        update.setId(req.getId());
        update.setStatus(EnableStatusEnum.DELETE.getVal());
        logOperationService.updateSelective(update);
        return Response.success();
    }

}
