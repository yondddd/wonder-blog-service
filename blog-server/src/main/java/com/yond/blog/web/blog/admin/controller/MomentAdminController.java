package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.MomentDO;
import com.yond.blog.service.MomentService;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description: 博客动态后台管理
 * @Author: Naccl
 * @Date: 2020-08-24
 */
@RestController
@RequestMapping("/admin")
public class MomentAdminController {
    @Autowired
    MomentService momentService;

    /**
     * 分页查询动态列表
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/moments")
    public Response moments(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<MomentDO> pageInfo = new PageInfo<>(momentService.getMomentList());
        return Response.ok("请求成功", pageInfo);
    }

    /**
     * 更新动态公开状态
     *
     * @param id        动态id
     * @param published 是否公开
     * @return
     */
    @OperationLogger("更新动态公开状态")
    @PutMapping("/moment/published")
    public Response updatePublished(@RequestParam Long id, @RequestParam Boolean published) {
        momentService.updateMomentPublishedById(id, published);
        return Response.ok("操作成功");
    }

    /**
     * 根据id查询动态
     *
     * @param id 动态id
     * @return
     */
    @GetMapping("/moment")
    public Response moment(@RequestParam Long id) {
        return Response.ok("获取成功", momentService.getMomentById(id));
    }

    /**
     * 删除动态
     *
     * @param id 动态id
     * @return
     */
    @OperationLogger("删除动态")
    @DeleteMapping("/moment")
    public Response deleteMoment(@RequestParam Long id) {
        momentService.deleteMomentById(id);
        return Response.ok("删除成功");
    }

    /**
     * 发布动态
     *
     * @param moment 动态实体
     * @return
     */
    @OperationLogger("发布动态")
    @PostMapping("/moment")
    public Response saveMoment(@RequestBody MomentDO moment) {
        if (moment.getCreateTime() == null) {
            moment.setCreateTime(new Date());
        }
        momentService.saveMoment(moment);
        return Response.ok("添加成功");
    }

    /**
     * 更新动态
     *
     * @param moment 动态实体
     * @return
     */
    @OperationLogger("更新动态")
    @PutMapping("/moment")
    public Response updateMoment(@RequestBody MomentDO moment) {
        if (moment.getCreateTime() == null) {
            moment.setCreateTime(new Date());
        }
        momentService.updateMoment(moment);
        return Response.ok("修改成功");
    }
}
