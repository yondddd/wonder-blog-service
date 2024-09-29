package com.yond.blog.web.view.controller;

import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.service.SiteConfigService;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.blog.web.view.vo.AboutVO;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.constant.AboutConstant;
import com.yond.common.enums.SiteConfigTypeEnum;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 关于我页面
 * @Author: Yond
 */
@RestController
@RequestMapping("/view/about")
public class AboutController {

    @Resource
    private SiteConfigService siteConfigService;

    @VisitLogger(VisitBehavior.ABOUT)
    @PostMapping("/config")
    public Response<AboutVO> config() {
        AboutVO data = new AboutVO();
        Map<String, String> map = siteConfigService.listByType(SiteConfigTypeEnum.ABOUT)
                .stream().collect(Collectors.toMap(SiteConfigDO::getKey, SiteConfigDO::getValue));
        data.setContent(MarkdownUtils.markdownToHtmlExtensions(map.get(AboutConstant.CONTENT)));
        data.setCommentEnabled(map.get(AboutConstant.COMMENT_ENABLED));
        return Response.success(data);
    }

}
