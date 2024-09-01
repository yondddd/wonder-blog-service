package com.yond.blog.service.impl;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.entity.VisitCityDO;
import com.yond.blog.entity.VisitRecordDO;
import com.yond.blog.service.*;
import com.yond.blog.web.blog.view.vo.CategoryBlogCount;
import com.yond.blog.web.blog.view.vo.TagBlogCount;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 仪表盘业务层实现
 * @Author: Naccl
 * @Date: 2020-10-08
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
    public int countVisitLogByToday() {
        return logVisitService.countVisitLogByToday();
    }

    @Override
    public int getBlogCount() {
        return blogService.countBlog();
    }

    @Override
    public int getCommentCount() {
        return commentService.countComment();
    }

    @Override
    public Map<String, List> getCategoryBlogCountMap() {
        //查询分类id对应的博客数量
        List<CategoryBlogCount> categoryBlogCountList = blogService.getCategoryBlogCountList();
        //查询所有分类的id和名称
        List<CategoryDO> categoryList = categoryService.listAll();
        //所有分类名称的List
        List<String> legend = new ArrayList<>();
        for (CategoryDO category : categoryList) {
            legend.add(category.getName());
        }
        //分类对应的博客数量List
        List<CategoryBlogCount> series = new ArrayList<>();
        if (categoryBlogCountList.size() == categoryList.size()) {
            Map<Long, String> m = new HashMap<>(16);
            for (CategoryDO c : categoryList) {
                m.put(c.getId(), c.getName());
            }
            for (CategoryBlogCount c : categoryBlogCountList) {
                c.setName(m.get(c.getId()));
                series.add(c);
            }
        } else {
            Map<Long, Integer> m = new HashMap<>(16);
            for (CategoryBlogCount c : categoryBlogCountList) {
                m.put(c.getId(), c.getValue());
            }
            for (CategoryDO c : categoryList) {
                CategoryBlogCount categoryBlogCount = new CategoryBlogCount();
                categoryBlogCount.setName(c.getName());
                Integer count = m.get(c.getId());
                if (count == null) {
                    categoryBlogCount.setValue(0);
                } else {
                    categoryBlogCount.setValue(count);
                }
                series.add(categoryBlogCount);
            }
        }
        Map<String, List> map = new HashMap<>(4);
        map.put("legend", legend);
        map.put("series", series);
        return map;
    }

    @Override
    public Map<String, List> getTagBlogCountMap() {
        //查询标签id对应的博客数量
        List<TagBlogCount> tagBlogCountList = blogTagService.getTagBlogCount();
        //查询所有标签的id和名称
        List<TagDO> tagList = tagService.listAll();
        //所有标签名称的List
        List<String> legend = new ArrayList<>();
        for (TagDO tag : tagList) {
            legend.add(tag.getName());
        }
        //标签对应的博客数量List
        List<TagBlogCount> series = new ArrayList<>();
        if (tagBlogCountList.size() == tagList.size()) {
            Map<Long, String> m = new HashMap<>(64);
            for (TagDO t : tagList) {
                m.put(t.getId(), t.getName());
            }
            for (TagBlogCount t : tagBlogCountList) {
                t.setName(m.get(t.getId()));
                series.add(t);
            }
        } else {
            Map<Long, Integer> m = new HashMap<>(64);
            for (TagBlogCount t : tagBlogCountList) {
                m.put(t.getId(), t.getValue());
            }
            for (TagDO t : tagList) {
                TagBlogCount tagBlogCount = new TagBlogCount();
                tagBlogCount.setName(t.getName());
                Integer count = m.get(t.getId());
                if (count == null) {
                    tagBlogCount.setValue(0);
                } else {
                    tagBlogCount.setValue(count);
                }
                series.add(tagBlogCount);
            }
        }
        Map<String, List> map = new HashMap<>(4);
        map.put("legend", legend);
        map.put("series", series);
        return map;
    }

    @Override
    public Map<String, List> getVisitRecordMap() {
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
        Map<String, List> map = new HashMap<>(8);
        map.put("date", date);
        map.put("pv", pv);
        map.put("uv", uv);
        return map;
    }

    @Override
    public List<VisitCityDO> getCityVisitorList() {
        return visitCityService.listAll();
    }

}
