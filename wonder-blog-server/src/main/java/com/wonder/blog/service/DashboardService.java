package com.wonder.blog.service;

import com.wonder.blog.web.admin.vo.StatisticCategoryVO;
import com.wonder.blog.web.admin.vo.StatisticCityVO;
import com.wonder.blog.web.admin.vo.StatisticTagVO;
import com.wonder.blog.web.admin.vo.StatisticVisitRecordVO;

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
