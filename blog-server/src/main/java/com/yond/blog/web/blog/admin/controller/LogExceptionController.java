package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.LogExceptionDO;
import com.yond.blog.service.LogExceptionService;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 异常日志后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin")
public class LogExceptionController {
    @Autowired
    LogExceptionService logExceptionService;

    /**
     * 分页查询异常日志列表
     *
     * @param date     按操作时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/exceptionLogs")
    public Response exceptionLogs(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<LogExceptionDO> pageInfo = new PageInfo<>(logExceptionService.getExceptionLogListByDate(startDate, endDate));
        return Response.ok("请求成功", pageInfo);
    }

    /**
     * 按id删除异常日志
     *
     * @param id 日志id
     * @return
     */
    @DeleteMapping("/exceptionLog")
    public Response delete(@RequestParam Long id) {
        logExceptionService.deleteExceptionLogById(id);
        return Response.ok("删除成功");
    }
}
