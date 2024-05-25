package com.yond.controller.view;

import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Result;
import com.yond.model.vo.BlogInfo;
import com.yond.model.vo.PageResult;
import com.yond.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 分类
 * @Author: Naccl
 * @Date: 2020-08-19
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
    public Result category(@RequestParam String categoryName,
                           @RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByCategoryNameAndIsPublished(categoryName, pageNum);
        return Result.ok("请求成功", pageResult);
    }
}
