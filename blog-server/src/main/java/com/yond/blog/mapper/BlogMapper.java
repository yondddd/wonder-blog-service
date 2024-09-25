package com.yond.blog.mapper;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.view.dto.BlogView;
import com.yond.blog.web.view.vo.BlogDetail;
import com.yond.blog.web.view.vo.RandomBlog;
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
    
    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(Integer limitNum);
    
    List<BlogView> getBlogViewsList();
    
    int updateViews(Long blogId, Integer views);
    
    BlogDetail getBlogByIdAndIsPublished(Long id);
    
    int countBlogByTagId(Long tagId);
    
}
