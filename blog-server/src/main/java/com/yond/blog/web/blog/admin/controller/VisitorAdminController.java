package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.VisitUserDO;
import com.yond.blog.service.VisitUserService;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 访客统计
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin")
public class VisitorAdminController {
    
    @Resource
    private VisitUserService visitUserService;
    
    
    @GetMapping("/visitors")
    public Response visitors(@RequestParam(defaultValue = "") String[] date,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        String startDate = null;
        String endDate = null;
        if (date.length == 2) {
            startDate = date[0];
            endDate = date[1];
        }
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<VisitUserDO> pageInfo = new PageInfo<>(visitUserService.listByDate(startDate, endDate));
        return Response.ok("请求成功", pageInfo);
    }
    
    
    @DeleteMapping("/visitor")
    public Response<Boolean> delete(@RequestParam Long id, @RequestParam String uuid) {
        visitUserService.deleteVisitor(id, uuid);
        return Response.success();
    }
    
}
