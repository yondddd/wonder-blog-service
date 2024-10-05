package com.wonder.blog.web.admin.controller;

import com.wonder.blog.entity.LogVisitDO;
import com.wonder.blog.service.LogVisitService;
import com.wonder.blog.web.admin.vo.LogVisitVO;
import com.wonder.blog.web.admin.convert.LogVisitConverter;
import com.wonder.blog.web.admin.req.LogVisitDelReq;
import com.wonder.blog.web.admin.req.LogVisitPageReq;
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
 * @Description: 访问日志后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/logVisit")
public class LogVisitController {

    @Resource
    private LogVisitService logVisitService;

    @PostMapping("/page")
    public PageResponse<List<LogVisitVO>> page(@RequestBody LogVisitPageReq req) {
        Pair<Integer, List<LogVisitDO>> pair = logVisitService.page(req.getUuid(), req.getStartDate(), req.getEndDate(), req.getPageNo(), req.getPageSize());
        List<LogVisitVO> data = pair.getRight().stream().map(LogVisitConverter::do2vo).toList();
        return PageResponse.<List<LogVisitVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }

    @PostMapping("/del")
    public Response<Boolean> del(@RequestBody LogVisitDelReq req) {
        LogVisitDO update = new LogVisitDO();
        update.setId(req.getId());
        update.setStatus(EnableStatusEnum.DELETE.getVal());
        logVisitService.updateSelective(update);
        return Response.success();
    }

}
