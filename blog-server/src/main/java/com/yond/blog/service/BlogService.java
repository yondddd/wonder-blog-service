package com.yond.blog.service;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.view.vo.ArchiveVO;
import com.yond.blog.web.view.vo.NewBlog;
import com.yond.blog.web.view.vo.RandomBlog;
import com.yond.blog.web.view.vo.SearchBlog;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface BlogService {
    
    Pair<Integer, List<BlogDO>> adminPageBy(String title,
                                            Long categoryId,
                                            Long tagId,
                                            Integer pageNo,
                                            Integer pageSize);
    
    Pair<Integer, List<BlogDO>> viewPageBy(Long categoryId,
                                           Long tagId,
                                           Integer pageNo,
                                           Integer pageSize);
    
    List<BlogDO> listByIds(List<Long> ids);
    
    List<SearchBlog> searchPublic(String query);
    
    List<NewBlog> listNewBlog();
    
    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
    
    BlogDO getBlogById(Long id);
    
    int countByTagId(Long tagId);
    
    List<BlogDO> listEnable();
    
    Long insertSelective(BlogDO blog);
    
    void updateSelective(BlogDO blog);
    
    void delById(Long id);
    
    ArchiveVO blogArchive();
    
    void incrBlogView(List<Long> blogIds, Integer incr);
    
}
