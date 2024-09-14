package com.yond.blog.mapper;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.view.dto.BlogView;
import com.yond.blog.web.blog.view.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    
    List<BlogDO> listByStatus(@Param("status") Integer status);
    
    List<BlogDO> listAll();
    
    int insertSelective(BlogDO blogDO);
    
    int updateSelective(BlogDO blogDO);
    
    List<BlogDO> getIdAndTitleList();
    
    List<NewBlog> getNewBlogListByIsPublished(@Param("limit") Integer limit);
    
    List<BlogInfo> getBlogInfoListByIsPublished(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
    
    List<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("categoryName") String categoryName);
    
    List<BlogInfo> getBlogInfoListByTagNameAndIsPublished(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("tagName") String tagName);
    
    List<String> getGroupYearMonthByIsPublished();
    
    List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);
    
    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(Integer limitNum);
    
    List<BlogView> getBlogViewsList();
    
    int updateViews(Long blogId, Integer views);
    
    BlogDetail getBlogByIdAndIsPublished(Long id);
    
    String getBlogPassword(Long blogId);
    
    int countBlogByIsPublished();
    
    int countBlogByTagId(Long tagId);
    
}
