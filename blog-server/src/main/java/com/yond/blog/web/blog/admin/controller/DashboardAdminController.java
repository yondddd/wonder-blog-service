package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.cache.remote.VisitCache;
import com.yond.blog.service.DashboardService;
import com.yond.blog.web.blog.admin.vo.StatisticDashboardVO;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Description: 后台管理仪表盘
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/statistic")
public class DashboardAdminController {
    
    @Resource
    private DashboardService dashboardService;
    @Resource
    private VisitCache visitCache;
    
    private static final Executor executor = Executors.newVirtualThreadPerTaskExecutor();
    
    @GetMapping("/dashboard")
    public Response<StatisticDashboardVO> dashboard() {
        StatisticDashboardVO data = new StatisticDashboardVO();
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> data.setPv(dashboardService.countVisitLogByToday()), executor),
                CompletableFuture.runAsync(() -> data.setUv(visitCache.identitySize()), executor),
                CompletableFuture.runAsync(() -> data.setBlogCount(dashboardService.getBlogCount()), executor),
                CompletableFuture.runAsync(() -> data.setCommentCount(dashboardService.getCommentCount()), executor),
                CompletableFuture.runAsync(() -> data.setCategory(dashboardService.getCategoryBlogCount()), executor),
                CompletableFuture.runAsync(() -> data.setTag(dashboardService.getTagBlogCount()), executor),
                CompletableFuture.runAsync(() -> data.setVisitRecord(dashboardService.getVisitRecord()), executor),
                CompletableFuture.runAsync(() -> data.setCityVisitor(dashboardService.getCityVisitorList()), executor)
        ).join();
        return Response.success(data);
    }
    
}
