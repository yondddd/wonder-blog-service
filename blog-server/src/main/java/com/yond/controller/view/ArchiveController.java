package com.yond.controller.view;

import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Result;
import com.yond.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description: 归档页面
 * @Author: Naccl
 * @Date: 2020-08-12
 */
@RestController
public class ArchiveController {
    @Autowired
    BlogService blogService;

    /**
     * 按年月分组归档公开博客 统计公开博客总数
     *
     * @return
     */
    @VisitLogger(VisitBehavior.ARCHIVE)
    @GetMapping("/view/archives")
    public Result archives() {
        Map<String, Object> archiveBlogMap = blogService.getArchiveBlogAndCountByIsPublished();
        return Result.ok("请求成功", archiveBlogMap);
    }
}
