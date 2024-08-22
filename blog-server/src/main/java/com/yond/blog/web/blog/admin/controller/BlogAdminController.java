package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.entity.UserDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.service.CommentService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.blog.admin.convert.BlogConverter;
import com.yond.blog.web.blog.admin.req.*;
import com.yond.blog.web.blog.admin.vo.BlogListVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 博客文章后台管理
 * @Author: Naccl
 * @Date: 2020-07-29
 */
@RestController
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;

    @PostMapping("/page")
    public @ResponseBody PageResponse<List<BlogListVO>> page(@RequestBody BlogListPageReq req) {

        Pair<Integer, List<BlogDO>> pair = blogService.pageByTitleLikeAndCategoryId(req.getTitle(), req.getCategoryId(),
                req.getPageNo(), req.getPageSize());
        List<BlogListVO> data = pair.getRight().stream().map(BlogConverter::convert).collect(Collectors.toList());
        List<Long> categoryIds = data.stream().map(BlogListVO::getCategoryId).map(Integer::longValue).toList();
        Map<Long, String> map = categoryService.listBtIds(categoryIds).stream().collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName, (key1, key2) -> key1));
        for (BlogListVO item : data) {
            item.setCategoryName(map.get(item.getCategoryId().longValue()));
        }
        return PageResponse.<List<BlogListVO>>custom().setData(data).setTotal(pair.getLeft()).setSuccess();
    }

    @PostMapping("/detail")
    public Response<BlogListVO> getBlog(@RequestBody BlogDetailReq req) {
        return Response.success(blog);
    }

    @OperationLogger("更新博客置顶状态")
    @PostMapping("/top")
    public Response<Boolean> top(@RequestBody BlogTopReq req) {
        BlogDO update = BlogDO.custom()
                .setId(req.getId())
                .setTop(req.getTop());
        blogService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("更新博客推荐状态")
    @PostMapping("/recommend")
    public Response<Boolean> recommend(@RequestBody BlogRecommendReq req) {
        BlogDO update = BlogDO.custom()
                .setId(req.getId())
                .setRecommend(req.getRecommend());
        blogService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("更新博客可见性状态")
    @PostMapping("/visible")
    public Response<Boolean> visible(@RequestBody BlogVisibleReq req) {
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


    /**
     * 删除博客文章、删除博客文章下的所有评论、同时维护 blog_tag 表
     *
     * @param id 文章id
     * @return
     */
    @OperationLogger("删除博客")
    @DeleteMapping("/blog")
    public Response<Boolean> delete(@RequestParam Long id) {
        blogService.deleteBlogTagByBlogId(id);
        blogService.deleteBlogById(id);
        commentService.deleteCommentsByBlogId(id);
        return Response.success();
    }


    /**
     * 保存草稿或发布新文章
     *
     * @param blog 博客文章DTO
     * @return
     */
    @OperationLogger("发布博客")
    @PostMapping("/blog")
    public Response saveBlog(@RequestBody com.yond.blog.web.blog.view.dto.Blog blog) {
        return getResult(blog, "save");
    }

    /**
     * 更新博客
     *
     * @param blog 博客文章DTO
     * @return
     */
    @OperationLogger("更新博客")
    @PutMapping("/blog")
    public Response updateBlog(@RequestBody com.yond.blog.web.blog.view.dto.Blog blog) {
        return getResult(blog, "update");
    }

    /**
     * 执行博客添加或更新操作：校验参数是否合法，添加分类、标签，维护博客标签关联表
     *
     * @param blog 博客文章DTO
     * @param type 添加或更新
     * @return
     */
    private Response getResult(com.yond.blog.web.blog.view.dto.Blog blog, String type) {
        //验证普通字段
        if (StringUtils.isBlank(blog.getTitle()) || StringUtils.isBlank(blog.getFirstPicture()) || StringUtils.isBlank(blog.getContent()) ||
                StringUtils.isBlank(blog.getDescription())
                || blog.getWords() == null || blog.getWords() < 0) {
            return Response.failure("参数有误");
        }

        //处理分类
        Object cate = blog.getCate();
        if (cate == null) {
            return Response.failure("分类不能为空");
        }
        if (cate instanceof Integer) {//选择了已存在的分类
            CategoryDO c = categoryService.getById(((Integer) cate).longValue());
            blog.setCategory(c);
        } else if (cate instanceof String) {//添加新分类
            //查询分类是否已存在
            CategoryDO category = categoryService.getByName((String) cate);
            if (category != null) {
                return Response.failure("不可添加已存在的分类");
            }
            CategoryDO c = new CategoryDO();
            c.setName((String) cate);
            categoryService.save(c);
            blog.setCategory(c);
        } else {
            return Response.failure("分类不正确");
        }

        //处理标签
        List<Object> tagList = blog.getTagList();
        List<TagDO> tags = new ArrayList<>();
        for (Object t : tagList) {
            if (t instanceof Integer) {//选择了已存在的标签
                TagDO tag = tagService.getTagById(((Integer) t).longValue());
                tags.add(tag);
            } else if (t instanceof String) {//添加新标签
                //查询标签是否已存在
                TagDO tag1 = tagService.getTagByName((String) t);
                if (tag1 != null) {
                    return Response.failure("不可添加已存在的标签");
                }
                TagDO tag = new TagDO();
                tag.setName((String) t);
                tagService.saveTag(tag);
                tags.add(tag);
            } else {
                return Response.failure("标签不正确");
            }
        }

        Date date = new Date();
        if (blog.getReadTime() == null || blog.getReadTime() < 0) {
            blog.setReadTime((int) Math.round(blog.getWords() / 200.0));//粗略计算阅读时长
        }
        if (blog.getViews() == null || blog.getViews() < 0) {
            blog.setViews(0);
        }
        if ("save".equals(type)) {
            blog.setCreateTime(date);
            blog.setUpdateTime(date);
            UserDO user = new UserDO();
            user.setId(1L);//个人博客默认只有一个作者
            blog.setUser(user);

            blogService.saveBlog(blog);
            //关联博客和标签(维护 blog_tag 表)
            for (TagDO t : tags) {
                blogService.saveBlogTag(blog.getId(), t.getId());
            }
            return Response.ok("添加成功");
        } else {
            blog.setUpdateTime(date);
            blogService.updateBlog(blog);
            //关联博客和标签(维护 blog_tag 表)
            blogService.deleteBlogTagByBlogId(blog.getId());
            for (TagDO t : tags) {
                blogService.saveBlogTag(blog.getId(), t.getId());
            }
            return Response.ok("更新成功");
        }
    }
}
