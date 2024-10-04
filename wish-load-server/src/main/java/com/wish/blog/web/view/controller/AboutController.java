package com.wish.blog.web.view.controller;

import com.wish.blog.entity.SiteConfigDO;
import com.wish.blog.service.SiteConfigService;
import com.wish.blog.util.markdown.MarkdownUtils;
import com.wish.blog.web.view.vo.AboutVO;
import com.wish.common.annotation.VisitLogger;
import com.wish.common.constant.AboutConstant;
import com.wish.common.constant.SiteConfigConstant;
import com.wish.common.enums.SiteConfigTypeEnum;
import com.wish.common.enums.VisitBehavior;
import com.wish.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/config")
    public Response<AboutVO> config() {
        AboutVO data = new AboutVO();
        Map<String, String> map = siteConfigService.listByType(SiteConfigTypeEnum.ABOUT)
                .stream().collect(Collectors.toMap(SiteConfigDO::getKey, SiteConfigDO::getValue));
        data.setContent(MarkdownUtils.markdownToHtmlExtensions(map.get(AboutConstant.CONTENT)));
        data.setCommentEnabled(map.get(SiteConfigConstant.ABOUT_COMMENT_ENABLED));
        data.setTitle(map.get(SiteConfigConstant.ABOUT_TITLE));
        data.setMusicId(map.get(SiteConfigConstant.ABOUT_MUSIC_ID));
        return Response.success(data);
    }

}
