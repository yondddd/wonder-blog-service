package com.yond.blog.service;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.view.vo.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Pair<Integer, List<BlogDO>> pageByTitleLikeAndCategoryId(String title, Integer categoryId, Integer pageNo, Integer pageSize);

    List<BlogDO> listByIds(List<Long> ids);

    List<SearchBlog> searchPublic(String query);

    List<BlogDO> getIdAndTitleList();

    List<NewBlog> getNewBlogListByIsPublished();

    PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum);

    PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum);

    PageResult<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName, Integer pageNum);

    Map<String, Object> getArchiveBlogAndCountByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();

    void updateViewsToRedis(Long blogId);

    void updateViews(Long blogId, Integer views);

    BlogDO getBlogById(Long id);

    String getTitleByBlogId(Long id);

    BlogDetail getBlogByIdAndIsPublished(Long id);

    String getBlogPassword(Long blogId);

    int countBlogByIsPublished();

    int countByTagId(Long tagId);

    Boolean getCommentEnabledByBlogId(Long blogId);

    Boolean getPublishedByBlogId(Long blogId);

    int countBlog();

    List<CategoryBlogCount> getCategoryBlogCountList();

    List<BlogDO> listEnable();

    Long insertSelective(BlogDO blog);

    void updateSelective(BlogDO blog);

    void delById(Long id);
}
