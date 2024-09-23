package com.yond.blog.service;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.view.vo.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface BlogService {
    
    Pair<Integer, List<BlogDO>> adminPageBy(String title,
                                            Integer categoryId,
                                            Integer tagId,
                                            Integer pageNo,
                                            Integer pageSize);
    
    Pair<Integer, List<BlogDO>> viewPageBy(String title,
                                           Integer categoryId,
                                           Integer tagId,
                                           Integer pageNo,
                                           Integer pageSize);
    
    List<BlogDO> listByIds(List<Long> ids);
    
    List<SearchBlog> searchPublic(String query);
    
    List<NewBlog> getNewBlogListByIsPublished();
    
    PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum);
    
    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
    
    void updateViewsToRedis(Long blogId);
    
    void updateViews(Long blogId, Integer views);
    
    BlogDO getBlogById(Long id);
    
    BlogDetail getBlogByIdAndIsPublished(Long id);
    
    int countByTagId(Long tagId);
    
    List<BlogDO> listEnable();
    
    Long insertSelective(BlogDO blog);
    
    void updateSelective(BlogDO blog);
    
    void delById(Long id);
    
    ArchiveVO blogArchive();
}
