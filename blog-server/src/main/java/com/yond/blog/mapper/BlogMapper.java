package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.view.dto.BlogView;
import com.yond.blog.web.blog.view.dto.BlogVisibility;
import com.yond.blog.web.blog.view.vo.ArchiveBlog;
import com.yond.blog.web.blog.view.vo.BlogDetail;
import com.yond.blog.web.blog.view.vo.BlogInfo;
import com.yond.blog.web.blog.view.vo.CategoryBlogCount;
import com.yond.blog.web.blog.view.vo.NewBlog;
import com.yond.blog.web.blog.view.vo.RandomBlog;
import com.yond.blog.web.blog.view.vo.SearchBlog;

import java.util.List;

/**
 * @Description: 博客文章持久层接口
 * @Author: Naccl
 * @Date: 2020-07-26
 */
@Mapper
@Repository
public interface BlogMapper {
	List<BlogDO> getListByTitleAndCategoryId(String title, Integer categoryId);

	List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query);

	List<BlogDO> getIdAndTitleList();

	List<NewBlog> getNewBlogListByIsPublished();

	List<BlogInfo> getBlogInfoListByIsPublished();

	List<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName);

	List<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName);

	List<String> getGroupYearMonthByIsPublished();

	List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);

	List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(Integer limitNum);

	List<BlogView> getBlogViewsList();

	int deleteBlogById(Long id);

	int deleteBlogTagByBlogId(Long blogId);

	int saveBlog(com.yond.blog.web.blog.view.dto.Blog blog);

	int saveBlogTag(Long blogId, Long tagId);

	int updateBlogRecommendById(Long blogId, Boolean recommend);

	int updateBlogVisibilityById(Long blogId, BlogVisibility bv);

	int updateBlogTopById(Long blogId, Boolean top);

	int updateViews(Long blogId, Integer views);

	BlogDO getBlogById(Long id);

	String getTitleByBlogId(Long id);

	BlogDetail getBlogByIdAndIsPublished(Long id);

	String getBlogPassword(Long blogId);

	int updateBlog(com.yond.blog.web.blog.view.dto.Blog blog);

	int countBlog();

	int countBlogByIsPublished();

	int countBlogByCategoryId(Long categoryId);

	int countBlogByTagId(Long tagId);

	Boolean getCommentEnabledByBlogId(Long blogId);

	Boolean getPublishedByBlogId(Long blogId);

	List<CategoryBlogCount> getCategoryBlogCountList();
}
