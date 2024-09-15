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
 * @Description: 分类
 * @Author: Yond
 */
@RestController
public class CategoryController {
    @Autowired
    BlogService blogService;

    /**
     * 根据分类name分页查询公开博客列表
     *
     * @param categoryName 分类name
     * @param pageNum      页码
     * @return
     */
    @VisitLogger(VisitBehavior.CATEGORY)
    @GetMapping("/view/category")
    public Response category(@RequestParam String categoryName,
                             @RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByCategoryNameAndIsPublished(categoryName, pageNum);
        return Response.ok("请求成功", pageResult);
    }
}
