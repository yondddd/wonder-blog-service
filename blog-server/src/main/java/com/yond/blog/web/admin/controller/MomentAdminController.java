package com.yond.blog.web.admin.controller;

import com.yond.blog.entity.MomentDO;
import com.yond.blog.service.MomentService;
import com.yond.blog.web.admin.convert.MomentConverter;
import com.yond.blog.web.admin.req.MomentDelReq;
import com.yond.blog.web.admin.req.MomentDetailReq;
import com.yond.blog.web.admin.req.MomentPageReq;
import com.yond.blog.web.admin.req.MomentPublishedReq;
import com.yond.blog.web.admin.vo.MomentVO;
import com.yond.common.annotation.OperationLogger;
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
 * @Description: 博客动态后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/moment")
public class MomentAdminController {

    @Resource
    private MomentService momentService;

    @PostMapping("/page")
    public PageResponse<List<MomentVO>> moments(@RequestBody MomentPageReq req) {
        Pair<Integer, List<MomentDO>> pair = momentService.page(true, false, req.getPageNo(), req.getPageSize());
        List<MomentVO> data = pair.getRight().stream().map(MomentConverter::do2vo).toList();
        return PageResponse.<List<MomentVO>>custom().setSuccess().setData(data).setTotal(pair.getLeft());
    }

    @PostMapping("/detail")
    public Response<MomentVO> moment(@RequestBody MomentDetailReq req) {
        MomentDO moment = momentService.getById(req.getId());
        return Response.success(MomentConverter.do2vo(moment));
    }

    @OperationLogger("更新动态公开状态")
    @PostMapping("/published")
    public Response<Boolean> updatePublished(@RequestBody MomentPublishedReq req) {
        MomentDO update = MomentDO.custom()
                .setId(req.getId())
                .setPublished(req.getPublished());
        momentService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("删除动态")
    @PostMapping("/del")
    public Response<Boolean> deleteMoment(@RequestBody MomentDelReq req) {
        MomentDO update = MomentDO.custom()
                .setId(req.getId())
                .setStatus(EnableStatusEnum.DELETE.getVal());
        momentService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("新增动态")
    @PostMapping("/save")
    public Response<Boolean> saveMoment(@RequestBody MomentVO moment) {
        momentService.insertSelective(MomentConverter.vo2do(moment));
        return Response.success();
    }

    @OperationLogger("更新动态")
    @PostMapping("/update")
    public Response<Boolean> updateMoment(@RequestBody MomentVO moment) {
        momentService.updateSelective(MomentConverter.vo2do(moment));
        return Response.success();
    }

}
