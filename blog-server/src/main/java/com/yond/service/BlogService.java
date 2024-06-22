package com.yond.service;

import com.yond.entity.Blog;
import com.yond.model.dto.BlogVisibility;
import com.yond.model.vo.*;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId);

    List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query);

    List<Blog> getIdAndTitleList();

    List<NewBlog> getNewBlogListByIsPublished();

    PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum);

    PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum);

    PageResult<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName, Integer pageNum);

    Map<String, Object> getArchiveBlogAndCountByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();

    void deleteBlogById(Long id);

    void deleteBlogTagByBlogId(Long blogId);

    void saveBlog(com.yond.model.dto.Blog blog);

    void saveBlogTag(Long blogId, Long tagId);

    void updateBlogRecommendById(Long blogId, Boolean recommend);

    void updateBlogVisibilityById(Long blogId, BlogVisibility blogVisibility);

    void updateBlogTopById(Long blogId, Boolean top);

    void updateViewsToRedis(Long blogId);

    void updateViews(Long blogId, Integer views);

    Blog getBlogById(Long id);

    String getTitleByBlogId(Long id);

    BlogDetail getBlogByIdAndIsPublished(Long id);

    String getBlogPassword(Long blogId);

    void updateBlog(com.yond.model.dto.Blog blog);

    int countBlogByIsPublished();

    int countBlogByCategoryId(Long categoryId);

    int countBlogByTagId(Long tagId);

    Boolean getCommentEnabledByBlogId(Long blogId);

    Boolean getPublishedByBlogId(Long blogId);

    int countBlog();

    List<CategoryBlogCount> getCategoryBlogCountList();
}
