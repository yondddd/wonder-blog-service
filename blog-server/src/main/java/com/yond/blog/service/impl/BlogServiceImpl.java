package com.yond.blog.service.impl;

import com.github.pagehelper.PageInfo;
import com.yond.blog.cache.local.BlogCache;
import com.yond.blog.cache.remote.BlogViewCache;
import com.yond.blog.entity.BlogDO;
import com.yond.blog.mapper.BlogMapper;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.BlogTagService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.service.TagService;
import com.yond.blog.util.JacksonUtils;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.blog.web.blog.view.dto.BlogView;
import com.yond.blog.web.blog.view.vo.*;
import com.yond.common.constant.BlogConstant;
import com.yond.common.enums.EnableStatusEnum;
import com.yond.common.exception.NotFoundException;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 博客文章业务层实现
 * @Author: Naccl
 * @Date: 2020-07-29
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;
    @Resource
    private TagService tagService;
    @Resource
    private BlogViewCache blogViewCache;
    @Resource
    private CategoryService categoryService;
    @Resource
    private BlogTagService blogTagService;

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
    public Pair<Integer, List<BlogDO>> pageByTitleLikeAndCategoryId(String title, Integer categoryId, Integer pageNo, Integer pageSize) {
        List<BlogDO> list = this.listEnable().stream()
                .filter(x -> StringUtils.isBlank(title) || x.getTitle().contains(title))
                .filter(x -> categoryId == null || categoryId.equals(x.getCategoryId()))
                .sorted(Comparator.comparing(BlogDO::getCreateTime).reversed())
                .collect(Collectors.toList());
        return Pair.of(list.size(), PageUtil.pageList(list, pageNo, pageSize));
    }

    @Override
    public List<SearchBlog> searchPublic(String query) {
        String lowerCase = query.toLowerCase();
        List<BlogDO> searchBlogs = this.listEnable().stream()
                .filter(BlogDO::getPublished)
                .filter(x -> StringUtils.isBlank(x.getPassword()))
                .filter(x -> x.getContent().toLowerCase().contains(lowerCase))
                .toList();
        List<SearchBlog> result = new ArrayList<>();
        // 数据库的处理是不区分大小写的，那么这里的匹配串处理也应该不区分大小写，否则会出现不准确的结果
        for (BlogDO searchBlog : searchBlogs) {
            SearchBlog item = new SearchBlog();
            String content = searchBlog.getContent().toUpperCase();
            int contentLength = content.length();
            int index = content.indexOf(query) - 10;
            index = Math.max(index, 0);
            int end = index + 21;//以关键字字符串为中心返回21个字
            end = Math.min(end, contentLength - 1);
            item.setContent(searchBlog.getContent().substring(index, end));
            item.setTitle(searchBlog.getTitle());
            item.setId(searchBlog.getId());
            result.add(item);
        }
        return result;
    }

    @Override
    public List<BlogDO> getIdAndTitleList() {
        List<BlogDO> all = this.listEnable();
        all.sort(Comparator.comparing(BlogDO::getCreateTime).reversed());
        List<BlogDO> result = new ArrayList<>();
        for (BlogDO blogDO : all) {
            BlogDO item = new BlogDO();
            item.setId(blogDO.getId());
            item.setTitle(blogDO.getTitle());
            result.add(item);
        }
        return result;
    }

    @Override
    public List<NewBlog> getNewBlogListByIsPublished() {

        List<BlogDO> collect = this.listEnable()
                .stream().filter(BlogDO::getPublished)
                .sorted(Comparator.comparing(BlogDO::getCreateTime).reversed())
                .toList();
        collect = collect.subList(0, Math.min(collect.size(), BlogConstant.NEW_BLOG_PAGE_SIZE));
        List<NewBlog> result = new ArrayList<>();
        for (BlogDO blogDO : collect) {
            NewBlog item = new NewBlog();
            item.setId(blogDO.getId());
            item.setTitle(blogDO.getTitle());
            item.setPrivacy(StringUtils.isNotBlank(blogDO.getPassword()));
            result.add(item);
        }
        return result;
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum) {
        List<BlogDO> collect = this.listEnable().stream()
                .filter(BlogDO::getPublished)
                .sorted(Comparator.comparing(BlogDO::getTop).reversed())
                .sorted(Comparator.comparing(BlogDO::getCreateTime).reversed())
                .collect(Collectors.toList());


        List<BlogInfo> page = PageUtil.pageList(collect, pageNum, BlogConstant.PAGE_SIZE)
                .stream().map(this::do2BlogInfo).toList();

        return new PageResult<>(collect.size(), page);

    }

    private BlogInfo do2BlogInfo(BlogDO blogDO) {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogDO.getId());
        blogInfo.setTitle(blogDO.getTitle());
        blogInfo.setDescription(blogDO.getDescription());
        blogInfo.setCreateTime(blogDO.getCreateTime());
        // 取缓存里的
        Integer blogView = blogViewCache.getBlogView(blogDO.getId());
        if (blogView != null) {
            blogInfo.setViews(blogDO.getViews());
        }
        blogInfo.setWords(blogDO.getWords());
        blogInfo.setReadTime(blogDO.getReadTime());
        blogInfo.setTop(blogDO.getTop());
        if (StringUtils.isNotBlank(blogDO.getPassword())) {
            blogInfo.setPrivacy(true);
            blogInfo.setPassword("");
            blogInfo.setDescription(BlogConstant.PRIVATE_BLOG_DESCRIPTION);
        } else {
            blogInfo.setPrivacy(false);
            blogInfo.setDescription(MarkdownUtils.markdownToHtmlExtensions(blogDO.getDescription()));
        }
        blogInfo.setCategory(categoryService.getById(blogDO.getCategoryId().longValue()));
        blogInfo.setTags(tagService.getTagListByBlogId(blogDO.getId()));
        return blogInfo;
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
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByCategoryNameAndIsPublished(pageNum, BlogConstant.PAGE_SIZE, categoryName));
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        setBlogViewsFromRedisToPageResult(pageResult);
        return pageResult;
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName, Integer pageNum) {
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByTagNameAndIsPublished(pageNum, BlogConstant.PAGE_SIZE, tagName));
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
        BlogDO blog = this.listEnable()
                .stream().filter(x -> id.equals(x.getId())).findFirst().orElse(null);
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

    @Override
    public List<BlogDO> listEnable() {
        List<BlogDO> cache = BlogCache.listEnable();
        if (cache == null) {
            cache = blogMapper.listByStatus(EnableStatusEnum.ENABLE.getVal());
        }
        return cache;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long insertSelective(BlogDO blog) {
        blogMapper.insertSelective(blog);
        this.deleteBlogCache();
        return blog.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSelective(BlogDO blog) {
        blogMapper.updateSelective(blog);
        this.deleteBlogCache();
    }

    @Override
    public void delById(Long id) {
        BlogDO del = BlogDO.custom()
                .setId(id)
                .setStatus(EnableStatusEnum.DELETE.getVal());
        this.updateSelective(del);
        // 评论和关联tag不删除
        blogViewCache.deleteBlogView(id);
    }

    /**
     * 删除首页缓存、最新推荐缓存、归档页面缓存、博客浏览量缓存
     */
    private void deleteBlogCache() {
        BlogCache.delAllBlogs();
        BlogCache.delInfo();
        BlogCache.delBlogGroup();
    }

}
