package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.service.SiteConfigService;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.enums.SiteSettingTypeEnum;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AboutAdminController {

    @Resource
    private SiteConfigService siteConfigService;

    @GetMapping("/about")
    public Response<Map<String, String>> about() {
        Map<String, String> map = siteConfigService.listByType(SiteSettingTypeEnum.ABOUT)
                .stream().collect(Collectors.toMap(SiteConfigDO::getNameEn, SiteConfigDO::getValue));
        return Response.success(map);
    }

    @OperationLogger("修改关于我页面")
    @PutMapping("/about")
    public Response<Boolean> updateAbout(@RequestBody Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            siteConfigService.updateValue(entry.getKey(), entry.getValue());
        }
        return Response.success();
    }

}
