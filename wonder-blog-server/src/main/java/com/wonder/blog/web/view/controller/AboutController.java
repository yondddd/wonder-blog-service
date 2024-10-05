package com.wonder.blog.web.view.controller;

import com.wonder.blog.entity.SiteConfigDO;
import com.wonder.blog.service.SiteConfigService;
import com.wonder.blog.web.view.vo.AboutVO;
import com.wonder.common.annotation.VisitLogger;
import com.wonder.common.constant.AboutConstant;
import com.wonder.common.constant.SiteConfigConstant;
import com.wonder.common.enums.SiteConfigTypeEnum;
import com.wonder.common.enums.VisitBehavior;
import com.wonder.common.resp.Response;
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
        data.setContent(map.get(AboutConstant.CONTENT));
        data.setCommentEnabled(map.get(SiteConfigConstant.ABOUT_COMMENT_ENABLED));
        data.setTitle(map.get(SiteConfigConstant.ABOUT_TITLE));
        data.setMusicId(map.get(SiteConfigConstant.ABOUT_MUSIC_ID));
        return Response.success(data);
    }

}
