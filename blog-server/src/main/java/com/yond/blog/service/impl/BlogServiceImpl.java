package com.yond.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.cache.local.BlogCache;
import com.yond.blog.cache.remote.BlogViewCache;
import com.yond.blog.entity.BlogDO;
import com.yond.blog.mapper.BlogMapper;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.TagService;
import com.yond.blog.util.JacksonUtils;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.blog.web.blog.view.dto.BlogView;
import com.yond.blog.web.blog.view.dto.BlogVisibility;
import com.yond.blog.web.blog.view.vo.*;
import com.yond.common.constant.BlogConstant;
import com.yond.common.exception.NotFoundException;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 博客文章业务层实现
 * @Author: Naccl
 * @Date: 2020-07-29
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagService tagService;
    @Autowired
    BlogViewCache blogViewCache;

    /**
     * 项目启动时，保存所有博客的浏览量到Redis
     */
    @PostConstruct
    private void saveBlogViewsToRedis() {
        //Redis中没有存储博客浏览量的Hash
        if (!blogViewCache.existViewMap()) {
            //从数据库中读取并存入Redis
            Map<Long, Integer> blogViewsMap = getBlogViewsMap();
            blogViewCache.setViewMap(blogViewsMap);
        }
    }

    @Override
    public List<BlogDO> getListByTitleAndCategoryId(String title, Integer categoryId) {
        return blogMapper.getListByTitleAndCategoryId(title, categoryId);
    }

    @Override
    public List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query) {
        List<SearchBlog> searchBlogs = blogMapper.getSearchBlogListByQueryAndIsPublished(query);
        // 数据库的处理是不区分大小写的，那么这里的匹配串处理也应该不区分大小写，否则会出现不准确的结果
        query = query.toUpperCase();
        for (SearchBlog searchBlog : searchBlogs) {
            String content = searchBlog.getContent().toUpperCase();
            int contentLength = content.length();
            int index = content.indexOf(query) - 10;
            index = Math.max(index, 0);
            int end = index + 21;//以关键字字符串为中心返回21个字
            end = Math.min(end, contentLength - 1);
            searchBlog.setContent(searchBlog.getContent().substring(index, end));
        }
        return searchBlogs;
    }

    @Override
    public List<BlogDO> getIdAndTitleList() {
        return blogMapper.getIdAndTitleList();
    }

    @Override
    public List<NewBlog> getNewBlogListByIsPublished() {
        List<NewBlog> newBlogListFromCache = BlogCache.getNewList();
        if (newBlogListFromCache != null) {
            return newBlogListFromCache;
        }
        PageHelper.startPage(1, BlogConstant.NEW_BLOG_PAGE_SIZE);
        List<NewBlog> newBlogList = blogMapper.getNewBlogListByIsPublished();
        for (NewBlog newBlog : newBlogList) {
            if (!"".equals(newBlog.getPassword())) {
                newBlog.setPrivacy(true);
                newBlog.setPassword("");
            } else {
                newBlog.setPrivacy(false);
            }
        }
        BlogCache.setNewList(newBlogList);
        return newBlogList;
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum) {
        //redis已有当前页缓存
        PageResult<BlogInfo> pageResultFromRedis = BlogCache.getInfoByPage(pageNum);
        if (pageResultFromRedis != null) {
            setBlogViewsFromRedisToPageResult(pageResultFromRedis);
            return pageResultFromRedis;
        }
        //redis没有缓存，从数据库查询，并添加缓存
        PageHelper.startPage(pageNum, BlogConstant.PAGE_SIZE, BlogConstant.ORDER_BY);
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByIsPublished());
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        setBlogViewsFromRedisToPageResult(pageResult);
        //添加首页缓存
        BlogCache.setInfoByPage(pageNum, pageResult);
        return pageResult;
    }

    /**
     * 将pageResult中博客对象的浏览量设置为Redis中的最新值
     *
     * @param pageResult
     */
    private void setBlogViewsFromRedisToPageResult(PageResult<BlogInfo> pageResult) {
        List<BlogInfo> blogInfos = pageResult.getList();
        for (int i = 0; i < blogInfos.size(); i++) {
            BlogInfo blogInfo = JacksonUtils.convertValue(blogInfos.get(i), BlogInfo.class);
            Long blogId = blogInfo.getId();
            /**
             * 这里如果出现异常，通常是手动修改过 MySQL 而没有通过后台管理，导致 Redis 和 MySQL 不同步
             * 从 Redis 中查出了 null，强转 int 时出现 NullPointerException
             * 直接抛出异常比带着 bug 继续跑要好得多
             *
             * 解决步骤：
             * 1.结束程序
             * 2.删除 Redis DB 中 blogViewsMap 这个 key（或者直接清空对应的整个 DB）
             * 3.重新启动程序
             *
             * 具体请查看: https://github.com/Naccl/NBlog/issues/58
             */
            Integer blogView = blogViewCache.getBlogView(blogId);
            if (blogView != null) {
                blogInfo.setViews(blogView);
            }
            blogInfos.set(i, blogInfo);
        }
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum) {
        PageHelper.startPage(pageNum, BlogConstant.PAGE_SIZE, BlogConstant.ORDER_BY);
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByCategoryNameAndIsPublished(categoryName));
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        setBlogViewsFromRedisToPageResult(pageResult);
        return pageResult;
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName, Integer pageNum) {
        PageHelper.startPage(pageNum, BlogConstant.PAGE_SIZE, BlogConstant.ORDER_BY);
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByTagNameAndIsPublished(tagName));
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        setBlogViewsFromRedisToPageResult(pageResult);
        return pageResult;
    }

    private List<BlogInfo> processBlogInfosPassword(List<BlogInfo> blogInfos) {
        for (BlogInfo blogInfo : blogInfos) {
            if (!"".equals(blogInfo.getPassword())) {
                blogInfo.setPrivacy(true);
                blogInfo.setPassword("");
                blogInfo.setDescription(BlogConstant.PRIVATE_BLOG_DESCRIPTION);
            } else {
                blogInfo.setPrivacy(false);
                blogInfo.setDescription(MarkdownUtils.markdownToHtmlExtensions(blogInfo.getDescription()));
            }
            blogInfo.setTags(tagService.getTagListByBlogId(blogInfo.getId()));
        }
        return blogInfos;
    }

    @Override
    public Map<String, Object> getArchiveBlogAndCountByIsPublished() {

        Map<String, Object> mapFromRedis = BlogCache.getBlogGroup();
        if (mapFromRedis != null) {
            return mapFromRedis;
        }
        List<String> groupYearMonth = blogMapper.getGroupYearMonthByIsPublished();
        Map<String, List<ArchiveBlog>> archiveBlogMap = new LinkedHashMap<>();
        for (String s : groupYearMonth) {
            List<ArchiveBlog> archiveBlogs = blogMapper.getArchiveBlogListByYearMonthAndIsPublished(s);
            for (ArchiveBlog archiveBlog : archiveBlogs) {
                if (!"".equals(archiveBlog.getPassword())) {
                    archiveBlog.setPrivacy(true);
                    archiveBlog.setPassword("");
                } else {
                    archiveBlog.setPrivacy(false);
                }
            }
            archiveBlogMap.put(s, archiveBlogs);
        }
        Integer count = countBlogByIsPublished();
        Map<String, Object> map = new HashMap<>(4);
        map.put("blogMap", archiveBlogMap);
        map.put("count", count);
        BlogCache.setBlogGroup(map);
        return map;
    }

    @Override
    public List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend() {
        List<RandomBlog> randomBlogs = blogMapper.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(BlogConstant.RANDOM_BLOG_LIMIT_NUM);
        for (RandomBlog randomBlog : randomBlogs) {
            if (!"".equals(randomBlog.getPassword())) {
                randomBlog.setPrivacy(true);
                randomBlog.setPassword("");
            } else {
                randomBlog.setPrivacy(false);
            }
        }
        return randomBlogs;
    }

    private Map<Long, Integer> getBlogViewsMap() {
        List<BlogView> blogViewList = blogMapper.getBlogViewsList();
        Map<Long, Integer> blogViewsMap = new HashMap<>(128);
        for (BlogView blogView : blogViewList) {
            blogViewsMap.put(blogView.getId(), blogView.getViews());
        }
        return blogViewsMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBlogById(Long id) {
        if (blogMapper.deleteBlogById(id) != 1) {
            throw new NotFoundException("该博客不存在");
        }
        deleteBlogRedisCache();
        blogViewCache.deleteBlogView(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBlogTagByBlogId(Long blogId) {
        if (blogMapper.deleteBlogTagByBlogId(blogId) == 0) {
            throw new PersistenceException("维护博客标签关联表失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBlog(com.yond.blog.web.blog.view.dto.Blog blog) {
        if (blogMapper.saveBlog(blog) != 1) {
            throw new PersistenceException("添加博客失败");
        }
        blogViewCache.setBlogView(blog.getId(), 0);
        deleteBlogRedisCache();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBlogTag(Long blogId, Long tagId) {
        if (blogMapper.saveBlogTag(blogId, tagId) != 1) {
            throw new PersistenceException("维护博客标签关联表失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlogRecommendById(Long blogId, Boolean recommend) {
        if (blogMapper.updateBlogRecommendById(blogId, recommend) != 1) {
            throw new PersistenceException("操作失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlogVisibilityById(Long blogId, BlogVisibility blogVisibility) {
        if (blogMapper.updateBlogVisibilityById(blogId, blogVisibility) != 1) {
            throw new PersistenceException("操作失败");
        }
        BlogCache.delInfo();
        BlogCache.delBlogGroup();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlogTopById(Long blogId, Boolean top) {
        if (blogMapper.updateBlogTopById(blogId, top) != 1) {
            throw new PersistenceException("操作失败");
        }
        BlogCache.delInfo();
    }

    @Override
    public void updateViewsToRedis(Long blogId) {
        blogViewCache.blogViewIncr(blogId, 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateViews(Long blogId, Integer views) {
        if (blogMapper.updateViews(blogId, views) != 1) {
            throw new PersistenceException("更新失败");
        }
    }

    @Override
    public BlogDO getBlogById(Long id) {
        BlogDO blog = blogMapper.getBlogById(id);
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }
        /**
         * 将浏览量设置为Redis中的最新值
         * 这里如果出现异常，查看第 152 行注释说明
         * @see BlogServiceImpl#setBlogViewsFromRedisToPageResult
         */
        Integer blogView = blogViewCache.getBlogView(blog.getId());
        if (blogView != null) {
            blog.setViews(blogView);
        }
        return blog;
    }

    @Override
    public String getTitleByBlogId(Long id) {
        return blogMapper.getTitleByBlogId(id);
    }

    @Override
    public BlogDetail getBlogByIdAndIsPublished(Long id) {
        BlogDetail blog = blogMapper.getBlogByIdAndIsPublished(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        /**
         * 将浏览量设置为Redis中的最新值
         * 这里如果出现异常，查看第 152 行注释说明
         * @see BlogServiceImpl#setBlogViewsFromRedisToPageResult
         */
        Integer blogView = blogViewCache.getBlogView(blog.getId());
        if (blogView != null) {
            blog.setViews(blogView);
        }
        return blog;
    }

    @Override
    public String getBlogPassword(Long blogId) {
        return blogMapper.getBlogPassword(blogId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlog(com.yond.blog.web.blog.view.dto.Blog blog) {
        if (blogMapper.updateBlog(blog) != 1) {
            throw new PersistenceException("更新博客失败");
        }
        deleteBlogRedisCache();
        blogViewCache.setBlogView(blog.getId(), blog.getViews());
    }

    @Override
    public int countBlogByIsPublished() {
        return blogMapper.countBlogByIsPublished();
    }

    @Override
    public int countBlogByCategoryId(Long categoryId) {
        return blogMapper.countBlogByCategoryId(categoryId);
    }

    @Override
    public int countBlogByTagId(Long tagId) {
        return blogMapper.countBlogByTagId(tagId);
    }

    @Override
    public Boolean getCommentEnabledByBlogId(Long blogId) {
        return blogMapper.getCommentEnabledByBlogId(blogId);
    }

    @Override
    public Boolean getPublishedByBlogId(Long blogId) {
        return blogMapper.getPublishedByBlogId(blogId);
    }

    @Override
    public int countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public List<CategoryBlogCount> getCategoryBlogCountList() {
        return blogMapper.getCategoryBlogCountList();
    }

    /**
     * 删除首页缓存、最新推荐缓存、归档页面缓存、博客浏览量缓存
     */
    private void deleteBlogRedisCache() {
        BlogCache.delInfo();
        BlogCache.delNew();
        BlogCache.delBlogGroup();
    }
}
