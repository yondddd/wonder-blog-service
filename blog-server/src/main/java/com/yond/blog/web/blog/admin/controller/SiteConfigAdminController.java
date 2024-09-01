package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.service.SiteConfigService;
import com.yond.blog.web.blog.admin.convert.SiteSettingConverter;
import com.yond.blog.web.blog.admin.vo.SiteSettingVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 站点设置后台管理
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@RestController
@RequestMapping("/admin/siteSetting")
public class SiteConfigAdminController {

    @Resource
    private SiteConfigService siteConfigService;

    @PostMapping("/listAll")
    public Response<List<SiteSettingVO>> siteSettings() {
        List<SiteSettingVO> data = siteConfigService.listAll().stream()
                .map(SiteSettingConverter::do2vo).toList();
        return Response.success(data);
    }

    /**
     * 全量更新
     *
     * @param req id为空为新增，不为空则更新
     */
    @OperationLogger("更新站点配置信息")
    @PostMapping("/update")
    public Response<Boolean> updateAll(@RequestBody List<SiteSettingVO> req) {
        List<SiteConfigDO> list = req.stream().map(SiteSettingConverter::vo2do).toList();
        siteConfigService.coverUpdate(list);
        return Response.success();
    }

}
