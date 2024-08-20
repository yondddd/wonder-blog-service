package com.yond.blog.service;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.view.dto.BlogVisibility;
import com.yond.blog.web.blog.view.vo.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Pair<Integer, List<BlogDO>> pageByTitleLikeAndCategoryId(String title, Integer categoryId, Integer pageNo, Integer pageSize);

    List<SearchBlog> searchPublic(String query);

    List<BlogDO> getIdAndTitleList();

    List<NewBlog> getNewBlogListByIsPublished();

    PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum);

    PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum);

    PageResult<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName, Integer pageNum);

    Map<String, Object> getArchiveBlogAndCountByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();

    void deleteBlogById(Long id);

    void deleteBlogTagByBlogId(Long blogId);

    void saveBlog(com.yond.blog.web.blog.view.dto.Blog blog);

    void saveBlogTag(Long blogId, Long tagId);

    void updateBlogRecommendById(Long blogId, Boolean recommend);

    void updateBlogVisibilityById(Long blogId, BlogVisibility blogVisibility);

    void updateBlogTopById(Long blogId, Boolean top);

    void updateViewsToRedis(Long blogId);

    void updateViews(Long blogId, Integer views);

    BlogDO getBlogById(Long id);

    String getTitleByBlogId(Long id);

    BlogDetail getBlogByIdAndIsPublished(Long id);

    String getBlogPassword(Long blogId);

    void updateBlog(com.yond.blog.web.blog.view.dto.Blog blog);

    int countBlogByIsPublished();

    int countBlogByCategoryId(Long categoryId);

    int countBlogByTagId(Long tagId);

    Boolean getCommentEnabledByBlogId(Long blogId);

    Boolean getPublishedByBlogId(Long blogId);

    int countBlog();

    List<CategoryBlogCount> getCategoryBlogCountList();

    List<BlogDO> listAll();
}
