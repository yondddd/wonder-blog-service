package com.yond.controller.admin;

import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Result;
import com.yond.service.AboutService;
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
    public Result<Map<String, String>> about() {
        return Result.success(aboutService.getAbout(false));
    }

    /**
     * 修改关于我页面
     *
     * @param map
     * @return
     */
    @OperationLogger("修改关于我页面")
    @PutMapping("/about")
    public Result<Boolean> updateAbout(@RequestBody Map<String, String> map) {
        aboutService.updateAbout(map);
        return Result.success();
    }

}
