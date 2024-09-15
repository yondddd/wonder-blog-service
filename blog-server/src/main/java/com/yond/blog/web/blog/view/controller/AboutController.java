package com.yond.blog.web.blog.view.controller;

import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.service.SiteConfigService;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.constant.AboutConstant;
import com.yond.common.enums.SiteSettingTypeEnum;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 关于我页面
 * @Author: Yond
 */
@RestController
public class AboutController {

    private final SiteConfigService siteConfigService;

    public AboutController(SiteConfigService siteConfigService) {
        this.siteConfigService = siteConfigService;
    }

    /**
     * 获取关于我页面信息
     *
     * @return
     */
    @VisitLogger(VisitBehavior.ABOUT)
    @GetMapping("/view/about")
    public Response<Map<String, String>> about() {

        Map<String, String> map = siteConfigService.listByType(SiteSettingTypeEnum.ABOUT)
                .stream().collect(Collectors.toMap(SiteConfigDO::getNameEn, SiteConfigDO::getValue));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (AboutConstant.CONTENT_KEY.equals(entry.getKey())) {
                entry.setValue(MarkdownUtils.markdownToHtmlExtensions(entry.getValue()));
            }
        }
        return Response.success(map);
    }

}
