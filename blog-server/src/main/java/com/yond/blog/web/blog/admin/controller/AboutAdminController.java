package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.SiteSettingDO;
import com.yond.blog.service.SiteSettingService;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.enums.SiteSettingTypeEnum;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 关于我页面后台管理
 * @Author: Naccl
 * @Date: 2020-09-01
 */
@RestController
@RequestMapping("/admin")
public class AboutAdminController {

    @Resource
    private SiteSettingService siteSettingService;

    /**
     * 获取关于我页面配置
     *
     * @return
     */
    @GetMapping("/about")
    public Response<Map<String, String>> about() {
        Map<String, String> map = siteSettingService.listByType(SiteSettingTypeEnum.ABOUT)
                .stream().collect(Collectors.toMap(SiteSettingDO::getNameEn, SiteSettingDO::getValue));
        return Response.success(map);
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
        for (Map.Entry<String, String> entry : map.entrySet()) {
            siteSettingService.updateValue(entry.getKey(), entry.getValue());
        }
        return Response.success();
    }

}
