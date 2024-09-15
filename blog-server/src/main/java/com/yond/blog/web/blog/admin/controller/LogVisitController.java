package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.LogVisitDO;
import com.yond.blog.service.LogVisitService;
import com.yond.common.resp.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 访问日志后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin")
public class LogVisitController {
    @Autowired
    LogVisitService logVisitService;

    /**
     * 分页查询访问日志列表
     *
     * @param uuid     按访客标识码模糊查询
     * @param date     按访问时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/visitLogs")
    public Response visitLogs(@RequestParam(defaultValue = "") String uuid,
                              @RequestParam(defaultValue = "") String[] date,
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
        PageInfo<LogVisitDO> pageInfo = new PageInfo<>(logVisitService.getVisitLogListByUUIDAndDate(StringUtils.trim(uuid), startDate, endDate));
        return Response.ok("请求成功", pageInfo);
    }

    /**
     * 按id删除访问日志
     *
     * @param id 日志id
     * @return
     */
    @DeleteMapping("/visitLog")
    public Response delete(@RequestParam Long id) {
        logVisitService.deleteVisitLogById(id);
        return Response.ok("删除成功");
    }
}
