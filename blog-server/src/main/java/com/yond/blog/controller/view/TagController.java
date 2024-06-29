package com.yond.blog.controller.view;

import com.yond.blog.model.vo.BlogInfo;
import com.yond.blog.model.vo.PageResult;
import com.yond.blog.service.BlogService;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 标签
 * @Author: Naccl
 * @Date: 2020-08-17
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
    public Result<PageResult<BlogInfo>> tag(@RequestParam String tagName,
                                            @RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByTagNameAndIsPublished(tagName, pageNum);
        return Result.success(pageResult);
    }

}
