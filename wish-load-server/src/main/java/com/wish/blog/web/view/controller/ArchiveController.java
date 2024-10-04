package com.wish.blog.web.view.controller;

import com.wish.blog.service.BlogService;
import com.wish.blog.web.view.vo.ArchiveVO;
import com.wish.common.annotation.VisitLogger;
import com.wish.common.enums.VisitBehavior;
import com.wish.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 归档页面
 * @Author: Yond
 */
@RestController
@RequestMapping("/view/archive")
public class ArchiveController {

    @Resource
    private BlogService blogService;

    @VisitLogger(VisitBehavior.ARCHIVE)
    @GetMapping("/list")
    public Response<ArchiveVO> list() {
        return Response.success(blogService.blogArchive());
    }

}
