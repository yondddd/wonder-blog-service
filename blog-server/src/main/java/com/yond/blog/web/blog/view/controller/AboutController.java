package com.yond.blog.web.blog.view.controller;

import com.yond.blog.service.AboutService;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description: 关于我页面
 * @Author: Naccl
 * @Date: 2020-08-31
 */
@RestController
public class AboutController {
    @Autowired
    AboutService aboutService;

    /**
     * 获取关于我页面信息
     *
     * @return
     */
    @VisitLogger(VisitBehavior.ABOUT)
    @GetMapping("/view/about")
    public Response<Map<String, String>> about() {
        return Response.success(aboutService.getAbout(true));
    }

}
