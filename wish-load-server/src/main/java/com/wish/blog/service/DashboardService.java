package com.wish.blog.service;

import com.wish.blog.web.admin.vo.StatisticCategoryVO;
import com.wish.blog.web.admin.vo.StatisticCityVO;
import com.wish.blog.web.admin.vo.StatisticTagVO;
import com.wish.blog.web.admin.vo.StatisticVisitRecordVO;

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
