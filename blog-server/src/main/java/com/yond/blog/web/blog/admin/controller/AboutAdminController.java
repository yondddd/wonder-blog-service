package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.service.AboutService;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: 关于我页面后台管理
 * @Author: Naccl
 * @Date: 2020-09-01
 */
@RestController
@RequestMapping("/admin")
public class AboutAdminController {

    @Autowired
    private AboutService aboutService;

    /**
     * 获取关于我页面配置
     *
     * @return
     */
    @GetMapping("/about")
    public Response<Map<String, String>> about() {
        return Response.success(aboutService.getAbout(false));
    }

    /**
     * 修改关于我页面
     *
     * @param map
     * @return
     */
    @OperationLogger("修改关于我页面")
    @PutMapping("/about")
    public Response<Boolean> updateAbout(@RequestBody Map<String, String> map) {
        aboutService.updateAbout(map);
        return Response.success();
    }

}
