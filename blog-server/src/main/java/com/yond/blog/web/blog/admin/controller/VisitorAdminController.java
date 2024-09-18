package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.VisitUserDO;
import com.yond.blog.service.VisitUserService;
import com.yond.common.resp.Response;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 访客统计
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin")
public class VisitorAdminController {

    private final VisitUserService visitUserService;

    public VisitorAdminController(VisitUserService visitUserService) {
        this.visitUserService = visitUserService;
    }

    /**
     * 分页查询访客列表
     *
     * @param date     按最后访问时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
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

    /**
     * 按id删除访客
     * 按uuid删除Redis缓存
     *
     * @param id   访客id
     * @param uuid 访客uuid
     * @return
     */
    @DeleteMapping("/visitor")
    public Response<Boolean> delete(@RequestParam Long id, @RequestParam String uuid) {
        visitUserService.deleteVisitor(id, uuid);
        return Response.success();
    }

}
