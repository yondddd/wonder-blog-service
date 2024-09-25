package com.yond.blog.web.view.controller;

import com.yond.blog.service.BlogService;
import com.yond.blog.web.view.vo.ArchiveVO;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/list")
    public Response<ArchiveVO> list() {
        return Response.success(blogService.blogArchive());
    }
    
}
