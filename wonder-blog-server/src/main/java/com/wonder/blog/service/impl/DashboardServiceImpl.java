package com.wonder.blog.service.impl;

import com.wonder.blog.entity.*;
import com.wonder.blog.service.*;
import com.wonder.blog.web.admin.vo.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 仪表盘业务层实现
 * @Author: Yond
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    
    @Resource
    private BlogService blogService;
    @Resource
    private BlogTagService blogTagService;
    @Resource
    private CommentService commentService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;
    @Resource
    private LogVisitService logVisitService;
    @Resource
    private VisitRecordService visitRecordService;
    @Resource
    private VisitCityService visitCityService;
    //查询最近30天的记录
    private static final int visitRecordLimitNum = 30;
    
    @Override
    public Integer countVisitLogByToday() {
        return logVisitService.countVisitLogByToday();
    }
    
    @Override
    public Integer getBlogCount() {
        return blogService.listEnable().size();
    }
    
    @Override
    public Integer getCommentCount() {
        return commentService.listAll().size();
    }
    
    @Override
    public StatisticCategoryVO getCategoryBlogCount() {
        Map<Long, List<BlogDO>> map = blogService.listEnable().stream().collect(Collectors.groupingBy(BlogDO::getCategoryId));
        Map<Long, String> allCategoryMap = categoryService.listAll().stream()
                .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));
        List<String> legend = new ArrayList<>();
        List<StatisticCategorySeriesVO> series = new ArrayList<>();
        for (Map.Entry<Long, List<BlogDO>> entry : map.entrySet()) {
            String categoryName = allCategoryMap.remove(entry.getKey());
            legend.add(categoryName);
            StatisticCategorySeriesVO item = new StatisticCategorySeriesVO();
            item.setName(categoryName);
            item.setValue(entry.getValue().size());
            series.add(item);
        }
        // default 0
        for (Map.Entry<Long, String> entry : allCategoryMap.entrySet()) {
            String categoryName = entry.getValue();
            legend.add(categoryName);
            StatisticCategorySeriesVO item = new StatisticCategorySeriesVO();
            item.setName(categoryName);
            item.setValue(0);
            series.add(item);
        }
        StatisticCategoryVO data = new StatisticCategoryVO();
        data.setLegend(legend);
        data.setSeries(series);
        return data;
    }
    
    @Override
    public StatisticTagVO getTagBlogCount() {
        List<String> legend = new ArrayList<>();
        List<StatisticTagSeriesVO> series = new ArrayList<>();
        Map<Long, String> tagMap = tagService.listAll().stream()
                .collect(Collectors.toMap(TagDO::getId, TagDO::getName));
        Map<Long, List<BlogTagDO>> map = blogTagService.listAll().stream().collect(Collectors.groupingBy(BlogTagDO::getTagId));
        for (Map.Entry<Long, List<BlogTagDO>> entry : map.entrySet()) {
            String tagName = tagMap.remove(entry.getKey());
            legend.add(tagName);
            StatisticTagSeriesVO count = new StatisticTagSeriesVO();
            count.setName(tagName);
            count.setValue(entry.getValue().size());
            series.add(count);
        }
        for (Map.Entry<Long, String> entry : tagMap.entrySet()) {
            String tagName = entry.getValue();
            legend.add(tagName);
            StatisticTagSeriesVO count = new StatisticTagSeriesVO();
            count.setName(tagName);
            count.setValue(0);
            series.add(count);
        }
        StatisticTagVO data = new StatisticTagVO();
        data.setLegend(legend);
        data.setSeries(series);
        return data;
    }
    
    @Override
    public StatisticVisitRecordVO getVisitRecord() {
        List<VisitRecordDO> visitRecordList = visitRecordService.listByLimit(visitRecordLimitNum);
        List<String> date = new ArrayList<>(visitRecordList.size());
        List<Integer> pv = new ArrayList<>(visitRecordList.size());
        List<Integer> uv = new ArrayList<>(visitRecordList.size());
        for (int i = visitRecordList.size() - 1; i >= 0; i--) {
            VisitRecordDO visitRecord = visitRecordList.get(i);
            date.add(visitRecord.getDate());
            pv.add(visitRecord.getPv());
            uv.add(visitRecord.getUv());
        }
        StatisticVisitRecordVO data = new StatisticVisitRecordVO();
        data.setDate(date);
        data.setPv(pv);
        data.setUv(uv);
        return data;
    }
    
    @Override
    public List<StatisticCityVO> getCityVisitorList() {
        List<StatisticCityVO> data = new ArrayList<>();
        visitCityService.listAll().forEach(x -> {
            StatisticCityVO item = new StatisticCityVO();
            item.setCity(x.getCity());
            item.setUv(x.getUv());
            data.add(item);
        });
        return data;
    }
    
}
