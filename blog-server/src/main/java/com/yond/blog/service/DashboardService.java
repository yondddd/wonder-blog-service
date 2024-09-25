package com.yond.blog.service;

import com.yond.blog.web.admin.vo.StatisticCategoryVO;
import com.yond.blog.web.admin.vo.StatisticCityVO;
import com.yond.blog.web.admin.vo.StatisticTagVO;
import com.yond.blog.web.admin.vo.StatisticVisitRecordVO;

import java.util.List;

public interface DashboardService {
    
    Integer countVisitLogByToday();
    
    Integer getBlogCount();
    
    Integer getCommentCount();
    
    StatisticCategoryVO getCategoryBlogCount();
    
    StatisticTagVO getTagBlogCount();
    
    StatisticVisitRecordVO getVisitRecord();
    
    List<StatisticCityVO> getCityVisitorList();
    
}
