package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.service.LogVisitService;
import com.yond.blog.web.blog.admin.req.LogVisitPageReq;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public PageResponse<> visitLogs(@RequestBody LogVisitPageReq req) {
    
    }
    
    @PostMapping("/del")
    public Response delete(@RequestParam Long id) {
        logVisitService.deleteVisitLogById(id);
        return Response.ok("删除成功");
    }
    
}
