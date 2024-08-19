package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.SiteSettingDO;
import com.yond.blog.service.SiteSettingService;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.constant.SiteSettingConstant;
import com.yond.common.resp.Response;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 站点设置后台管理
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@RestController
@RequestMapping("/admin")
public class SiteSettingAdminController {

    private final SiteSettingService siteSettingService;

    public SiteSettingAdminController(SiteSettingService siteSettingService) {
        this.siteSettingService = siteSettingService;
    }

    /**
     * 获取所有站点配置信息
     *
     * @return
     */
    @GetMapping("/siteSettings")
    public Response<Map<String, List<SiteSettingDO>>> siteSettings() {
        Map<String, List<SiteSettingDO>> typeMap = siteSettingService.getListForAdmin();
        return Response.success(typeMap);
    }

    /**
     * 修改、删除(部分配置可为空，但不可删除)、添加(只能添加部分)站点配置
     *
     * @param map 包含所有站点信息更新后的数据 map => {settings=[更新后的所有配置List], deleteIds=[要删除的配置id List]}
     * @return
     */
    @OperationLogger("更新站点配置信息")
    @PostMapping("/siteSettings")
    public Response<Boolean> updateAll(@RequestBody Map<String, Object> map) {
        List<LinkedHashMap> siteSettings = (List<LinkedHashMap>) map.get("settings");
        List<Integer> deleteIds = (List<Integer>) map.get("deleteIds");
        siteSettingService.updateSiteSetting(siteSettings, deleteIds);
        return Response.success();
    }

    /**
     * 查询网页标题后缀
     *
     * @return
     */
    @GetMapping("/webTitleSuffix")
    public Response<String> getWebTitleSuffix() {
        String value = siteSettingService.getValue(SiteSettingConstant.WEB_TITLE_SUFFIX);
        return Response.success(value);
    }

}
