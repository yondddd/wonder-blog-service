package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.BlogTagService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.blog.admin.convert.BlogConverter;
import com.yond.blog.web.blog.admin.req.*;
import com.yond.blog.web.blog.admin.vo.BlogBaseVO;
import com.yond.blog.web.blog.admin.vo.BlogVO;
import com.yond.blog.web.blog.admin.vo.CategoryVO;
import com.yond.blog.web.blog.admin.vo.TagVO;
import com.yond.blog.web.handler.session.UserSession;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 博客文章后台管理
 * @Author: yond
 * @Date: 2020-07-29
 */
@RestController
@RequestMapping("/admin/blog")
public class BlogAdminController {
    
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private BlogTagService blogTagService;
    @Resource
    private TagService tagService;
    
    
    @PostMapping("/page")
    public @ResponseBody PageResponse<List<BlogVO>> page(@RequestBody BlogListPageReq req) {
        
        Pair<Integer, List<BlogDO>> pair = blogService.pageByTitleLikeAndCategoryId(req.getTitle(), req.getCategoryId(),
                req.getPageNo(), req.getPageSize());
        List<Long> categoryIds = pair.getRight().stream().map(BlogDO::getCategoryId).map(Integer::longValue).toList();
        Map<Long, String> map = categoryService.listByIds(categoryIds).stream()
                .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName, (key1, key2) -> key1));
        List<BlogVO> data = pair.getRight().stream()
                .map(x -> BlogConverter.do2vo(x, map.get(x.getCategoryId().longValue()), null)).toList();
        return PageResponse.<List<BlogVO>>custom().setData(data).setTotal(pair.getLeft()).setSuccess();
    }
    
    @PostMapping("/detail")
    public Response<BlogVO> getBlog(@RequestBody BlogDetailReq req) {
        BlogDO blog = blogService.getBlogById(req.getId());
        CategoryDO category = categoryService.getById(blog.getCategoryId().longValue());
        List<TagDO> blogTags = blogTagService.listTagsByBlogId(blog.getId());
        BlogVO data = BlogConverter.do2vo(blog, category.getName(), blogTags);
        return Response.success(data);
    }
    
    @PostMapping("/listAllBase")
    public Response<List<BlogBaseVO>> listAllBase() {
        List<BlogBaseVO> data = new ArrayList<>();
        List<BlogDO> all = blogService.listEnable();
        all.sort(Comparator.comparing(BlogDO::getId).reversed());
        all.forEach(x -> {
            BlogBaseVO item = new BlogBaseVO();
            item.setId(x.getId());
            item.setTitle(x.getTitle());
            data.add(item);
        });
        return Response.success(data);
    }
    
    @OperationLogger("更新博客置顶状态")
    @PostMapping("/top")
    public Response<Boolean> top(UserSession userSession, @RequestBody BlogTopReq req) {
        BlogDO update = BlogDO.custom()
                .setId(req.getId())
                .setTop(req.getTop());
        blogService.updateSelective(update);
        return Response.success();
    }
    
    @OperationLogger("更新博客推荐状态")
    @PostMapping("/recommend")
    public Response<Boolean> recommend(UserSession userSession, @RequestBody BlogRecommendReq req) {
        BlogDO update = BlogDO.custom()
                .setId(req.getId())
                .setRecommend(req.getRecommend());
        blogService.updateSelective(update);
        return Response.success();
    }
    
    @OperationLogger("更新博客可见性状态")
    @PostMapping("/visible")
    public Response<Boolean> visible(UserSession userSession, @RequestBody BlogVisibleReq req) {
        BlogDO update = BlogDO.custom()
                .setId(req.getId())
                .setAppreciation(req.getAppreciation())
                .setRecommend(req.getRecommend())
                .setCommentEnabled(req.getCommentEnabled())
                .setTop(req.getTop())
                .setPublished(req.getPublished())
                .setPassword(req.getPassword());
        blogService.updateSelective(update);
        return Response.success();
    }
    
    @OperationLogger("删除博客")
    @PostMapping("/del")
    public Response<Boolean> delete(UserSession userSession, @RequestBody BlogDelReq req) {
        blogService.delById(req.getId());
        return Response.success();
    }
    
    @OperationLogger("发布博客")
    @PostMapping("/save")
    public Response<Boolean> saveBlog(UserSession userSession, @RequestBody BlogSaveReq req) {
        Assert.notNull(userSession, "用户信息不能为null");
        Assert.isNull(req.getId(), "博客id应为null");
        this.checkBlogSaveParam(req);
        List<Long> tagIds = this.getTagIds(req.getTags());
        BlogDO insert = BlogConverter.save2do(req);
        insert.setCategoryId(this.geCategoryId(req.getCategory()).intValue());
        insert.setUserId(userSession.getUserId().intValue());
        Long blogId = blogService.insertSelective(insert);
        blogTagService.saveBlogTag(blogId, tagIds);
        return Response.success();
    }
    
    @OperationLogger("更新博客")
    @PostMapping("/update")
    public Response<Boolean> updateBlog(UserSession userSession, @RequestBody BlogSaveReq req) {
        Assert.notNull(userSession, "用户信息不能为null");
        Assert.notNull(req.getId(), "博客id不能为空");
        this.checkBlogSaveParam(req);
        List<Long> tagIds = this.getTagIds(req.getTags());
        BlogDO update = BlogConverter.save2do(req);
        update.setCategoryId(this.geCategoryId(req.getCategory()).intValue());
        update.setUserId(userSession.getUserId().intValue());
        blogService.updateSelective(update);
        blogTagService.saveBlogTag(update.getId(), tagIds);
        return Response.success();
    }
    
    private Long geCategoryId(CategoryVO category) {
        if (category.getId() != null) {
            return category.getId();
        }
        return categoryService.saveIfAbsent(category.getName());
    }
    
    private List<Long> getTagIds(List<TagVO> tags) {
        Assert.notEmpty(tags, "博客标签不能为空");
        List<Long> result = new ArrayList<>();
        for (TagVO tag : tags) {
            Long tagId = tag.getId();
            if (tagId == null) {
                TagDO tagDO = TagDO.custom();
                tagDO.setName(tag.getName());
                tagDO.setColor(tag.getColor());
                tagId = tagService.saveIfAbsent(tagDO);
            }
            result.add(tagId);
        }
        return result;
    }
    
    private void checkBlogSaveParam(BlogSaveReq req) {
        Assert.notNull(req, "请求为空");
        Assert.hasText(req.getTitle(), "标题不能为空");
        Assert.hasText(req.getFirstPicture(), "首图不能为空");
        Assert.hasText(req.getContent(), "内容不能为空");
        Assert.hasText(req.getDescription(), "描述不能为空");
        Assert.notNull(req.getPublished(), "是否公开不能为空");
        Assert.notNull(req.getRecommend(), "是否推荐不能为空");
        Assert.notNull(req.getAppreciation(), "是否赞赏不能为空");
        Assert.notNull(req.getCommentEnabled(), "是否开启评论不能为空");
        Assert.notNull(req.getTop(), "是否置顶不能为空");
        Assert.notNull(req.getViews(), "浏览次数不能为空");
        Assert.notNull(req.getWords(), "字数不能为空");
        Assert.notNull(req.getCategory(), "分类不能为空");
        Assert.notEmpty(req.getTags(), "标签不能为空");
    }
    
}
