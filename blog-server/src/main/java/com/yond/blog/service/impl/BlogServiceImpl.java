package com.yond.blog.service.impl;

import com.yond.blog.cache.local.BlogCache;
import com.yond.blog.cache.redis.BlogViewCache;
import com.yond.blog.entity.BlogDO;
import com.yond.blog.mapper.BlogMapper;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.BlogTagService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.blog.web.view.dto.BlogView;
import com.yond.blog.web.view.vo.*;
import com.yond.common.constant.BlogConstant;
import com.yond.common.enums.EnableStatusEnum;
import com.yond.common.exception.NotFoundException;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.date.DateUtils;
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
 * @Author: Yond
 */
@Service
public class BlogServiceImpl implements BlogService {
    
    @Resource
    private BlogMapper blogMapper;
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
    public Pair<Integer, List<BlogDO>> adminPageBy(String title,
                                                   Long categoryId,
                                                   Long tagId,
                                                   Integer pageNo,
                                                   Integer pageSize) {
        List<BlogDO> list = this.listEnable().stream()
                .filter(x -> StringUtils.isBlank(title) || x.getTitle().contains(title))
                .filter(x -> categoryId == null || categoryId.equals(x.getCategoryId()))
                .sorted(Comparator.comparing(BlogDO::getCreateTime).reversed())
                .collect(Collectors.toList());
        return Pair.of(list.size(), PageUtil.pageList(list, pageNo, pageSize));
    }
    
    @Override
    public Pair<Integer, List<BlogDO>> viewPageBy(Long categoryId,
                                                  Long tagId,
                                                  Integer pageNo,
                                                  Integer pageSize) {
        List<BlogDO> list = this.listEnable().stream()
                .filter(x -> categoryId == null || categoryId.equals(x.getCategoryId()))
                .sorted(Comparator.comparing(BlogDO::getCreateTime).reversed())
                .collect(Collectors.toList());
        return Pair.of(list.size(), PageUtil.pageList(list, pageNo, pageSize));
    }
    
    @Override
    public List<BlogDO> listByIds(List<Long> ids) {
        Set<Long> set = Set.copyOf(ids);
        return this.listEnable().stream().filter(x -> set.contains(x.getId())).collect(Collectors.toList());
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
        blogInfo.setTags(blogTagService.listTagsByBlogId(blogDO.getId()));
        return blogInfo;
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
        Integer blogView = blogViewCache.getBlogView(blog.getId());
        if (blogView != null) {
            blog.setViews(blogView);
        }
        return blog;
    }
    
    @Override
    public BlogDetail getBlogByIdAndIsPublished(Long id) {
        BlogDetail blog = blogMapper.getBlogByIdAndIsPublished(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        Integer blogView = blogViewCache.getBlogView(blog.getId());
        if (blogView != null) {
            blog.setViews(blogView);
        }
        return blog;
    }
    
    @Override
    public int countByTagId(Long tagId) {
        return blogMapper.countBlogByTagId(tagId);
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
    
    @Override
    public ArchiveVO blogArchive() {
        ArchiveVO cache = BlogCache.getBlogArchive();
        if (cache != null) {
            return cache;
        }
        ArchiveVO data = new ArchiveVO();
        List<BlogDO> all = this.listEnable().stream().filter(BlogDO::getPublished).toList();
        data.setTotal(all.size());
        List<ArchiveGroupVO> groups = new ArrayList<>();
        Map<String, List<BlogDO>> group = all.stream().collect(Collectors.groupingBy(x -> DateUtils.format(DateUtils.MONTH_FORMAT, x.getCreateTime())));
        for (Map.Entry<String, List<BlogDO>> entry : group.entrySet()) {
            ArchiveGroupVO item = new ArchiveGroupVO();
            item.setDateGroup(entry.getKey());
            List<ArchiveBlogVO> blogs = new ArrayList<>();
            List<BlogDO> blogList = entry.getValue();
            blogList.sort(Comparator.comparing(BlogDO::getId).reversed());
            for (BlogDO blog : blogList) {
                ArchiveBlogVO vo = new ArchiveBlogVO();
                vo.setId(blog.getId());
                vo.setTitle(blog.getTitle());
                vo.setDay(DateUtils.format(DateUtils.DAY_FORMAT, blog.getCreateTime()));
                vo.setPrivacy(StringUtils.isNotBlank(blog.getPassword()));
                blogs.add(vo);
            }
            item.setBlogs(blogs);
            groups.add(item);
        }
        data.setGroups(groups);
        BlogCache.setBlogArchive(data);
        return data;
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
