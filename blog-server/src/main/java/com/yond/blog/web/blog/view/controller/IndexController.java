package com.yond.blog.web.blog.view.controller;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.service.SiteSettingService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.blog.view.vo.NewBlog;
import com.yond.blog.web.blog.view.vo.RandomBlog;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 站点相关
 * @Author: Naccl
 * @Date: 2020-08-09
 */

@RestController
public class IndexController {
    @Autowired
    SiteSettingService siteSettingService;
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;

    /**
     * 获取站点配置信息、最新推荐博客、分类列表、标签云、随机博客
     *
     * @return
     */
    @GetMapping("/view/site")
    public Response site() {
        Map<String, Object> map = siteSettingService.getSiteInfoForView();
        List<NewBlog> newBlogList = blogService.getNewBlogListByIsPublished();
        List<CategoryDO> categoryList = categoryService.listAll();
        List<TagDO> tagList = tagService.getTagListNotId();
        List<RandomBlog> randomBlogList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
        map.put("newBlogList", newBlogList);
        map.put("categoryList", categoryList);
        map.put("tagList", tagList);
        map.put("randomBlogList", randomBlogList);
        return Response.ok("请求成功", map);
    }
}
