package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.LogLoginDO;
import com.yond.blog.service.LogLoginService;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 登录日志后台管理
 * @Author: Naccl
 * @Date: 2020-12-03
 */
@RestController
@RequestMapping("/admin")
public class LogLoginController {
    @Autowired
    LogLoginService logLoginService;

    /**
     * 分页查询登录日志列表
     *
     * @param date     按操作时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/loginLogs")
    public Response loginLogs(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<LogLoginDO> pageInfo = new PageInfo<>(logLoginService.getLoginLogListByDate(startDate, endDate));
        return Response.ok("请求成功", pageInfo);
    }

    /**
     * 按id删除登录日志
     *
     * @param id 日志id
     * @return
     */
    @DeleteMapping("/loginLog")
    public Response delete(@RequestParam Long id) {
        logLoginService.deleteLoginLogById(id);
        return Response.ok("删除成功");
    }
}
