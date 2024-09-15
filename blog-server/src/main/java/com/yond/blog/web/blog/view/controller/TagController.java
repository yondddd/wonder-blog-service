package com.yond.blog.web.blog.view.controller;

import com.yond.blog.service.BlogService;
import com.yond.blog.web.blog.view.vo.BlogInfo;
import com.yond.blog.web.blog.view.vo.PageResult;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 标签
 * @Author: Yond
 */
@RestController
public class TagController {
    @Autowired
    BlogService blogService;

    /**
     * 根据标签name分页查询公开博客列表
     *
     * @param tagName 标签name
     * @param pageNum 页码
     * @return
     */
    @VisitLogger(VisitBehavior.TAG)
    @GetMapping("/view/tag")
    public Response<PageResult<BlogInfo>> tag(@RequestParam String tagName,
                                              @RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByTagNameAndIsPublished(tagName, pageNum);
        return Response.success(pageResult);
    }

}
