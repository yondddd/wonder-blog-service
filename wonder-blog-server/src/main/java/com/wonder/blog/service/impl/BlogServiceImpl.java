package com.wonder.blog.service.impl;

import com.wonder.blog.cache.local.BlogCache;
import com.wonder.blog.entity.BlogDO;
import com.wonder.blog.entity.BlogTagDO;
import com.wonder.blog.mapper.BlogMapper;
import com.wonder.blog.service.BlogService;
import com.wonder.blog.service.BlogTagService;
import com.wonder.blog.web.view.vo.*;
import com.wonder.common.constant.BlogConstant;
import com.wonder.common.enums.EnableStatusEnum;
import com.wonder.common.exception.NotFoundException;
import com.wonder.common.utils.date.DateUtils;
import com.wonder.common.utils.page.PageUtil;
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
    private BlogTagService blogTagService;

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
                .sorted(Comparator.comparing(BlogDO::getTop).reversed().thenComparing(Comparator.comparing(BlogDO::getId).reversed()))
                .collect(Collectors.toList());
        if (tagId != null) {
            List<BlogTagDO> refs = blogTagService.listByTagId(tagId);
            Set<Long> blogIdSet = refs.stream().map(BlogTagDO::getBlogId).collect(Collectors.toSet());
            list = list.stream().filter(x -> blogIdSet.contains(x.getId())).collect(Collectors.toList());
        }
        return Pair.of(list.size(), PageUtil.pageList(list, pageNo, pageSize));
    }

    @Override
    public List<BlogDO> listByIds(List<Long> ids) {
        Set<Long> set = Set.copyOf(ids);
        return this.listEnable().stream().filter(x -> set.contains(x.getId())).collect(Collectors.toList());
    }

    @Override
    public BlogDO getBlogById(Long id) {
        BlogDO blog = this.listEnable()
                .stream().filter(x -> id.equals(x.getId())).findFirst().orElse(null);
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }
        return blog;
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
    }

    @Override
    public void incrBlogView(List<Long> blogIds, Integer incr) {
        blogMapper.incrBlogView(blogIds, incr);
    }

    @Override
    public List<SearchBlogVO> searchPublic(String query) {
        String lowerCase = query.toLowerCase();
        List<BlogDO> searchBlogs = this.listEnable().stream()
                .filter(BlogDO::getPublished)
                .filter(x -> StringUtils.isBlank(x.getPassword()))
                .filter(x -> x.getTitle().toLowerCase().contains(lowerCase) ||
                        x.getContent().toLowerCase().contains(lowerCase))
                .toList();
        List<SearchBlogVO> result = new ArrayList<>();
        // 数据库的处理是不区分大小写的，那么这里的匹配串处理也应该不区分大小写，否则会出现不准确的结果
        for (BlogDO searchBlog : searchBlogs) {
            SearchBlogVO item = new SearchBlogVO();
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
    public List<NewBlogVO> listNewBlog() {

        List<BlogDO> collect = this.listEnable()
                .stream().filter(BlogDO::getPublished)
                .sorted(Comparator.comparing(BlogDO::getCreateTime).reversed())
                .toList();
        collect = collect.subList(0, Math.min(collect.size(), BlogConstant.NEW_BLOG_PAGE_SIZE));
        List<NewBlogVO> result = new ArrayList<>();
        for (BlogDO blogDO : collect) {
            NewBlogVO item = new NewBlogVO();
            item.setId(blogDO.getId());
            item.setTitle(blogDO.getTitle());
            item.setPrivacy(StringUtils.isNotBlank(blogDO.getPassword()));
            result.add(item);
        }
        return result;
    }

    @Override
    public List<RandomBlogVO> listRandomBlog() {

        List<BlogDO> list = this.listEnable().stream().filter(BlogDO::getPublished).filter(BlogDO::getRecommend).collect(Collectors.toList());
        Collections.shuffle(list);
        List<BlogDO> page = PageUtil.pageList(list, 1, BlogConstant.RANDOM_BLOG_LIMIT_NUM);
        List<RandomBlogVO> result = new ArrayList<>();
        for (BlogDO b : page) {
            RandomBlogVO r = new RandomBlogVO();
            r.setId(b.getId());
            r.setTitle(b.getTitle());
            r.setFirstPicture(b.getFirstPicture());
            r.setCreateTime(b.getCreateTime());
            r.setPrivacy(StringUtils.isNotBlank(b.getPassword()));
            result.add(r);
        }
        return result;
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
        BlogCache.delBlogGroup();
    }

}
