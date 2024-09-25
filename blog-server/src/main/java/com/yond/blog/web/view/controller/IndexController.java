package com.yond.blog.web.view.controller;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.service.SiteConfigService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.view.vo.NewBlog;
import com.yond.blog.web.view.vo.RandomBlog;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 站点相关
 * @Author: Yond
 */
@RestController
public class IndexController {
    
    @Resource
    private SiteConfigService siteConfigService;
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;
    
    /**
     * 获取站点配置信息、最新推荐博客、分类列表、标签云、随机博客
     *
     * @return
     */
    @GetMapping("/view/site")
    public Response<Map<String, Object>> site() {
        Map<String, Object> map = siteConfigService.getSiteInfoForView();
        List<NewBlog> newBlogList = blogService.getNewBlogListByIsPublished();
        List<CategoryDO> categoryList = categoryService.listAll();
        List<TagDO> tagList = tagService.listAll();
        List<RandomBlog> randomBlogList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
        map.put("newBlogList", newBlogList);
        map.put("categoryList", categoryList);
        map.put("tagList", tagList);
        map.put("randomBlogList", randomBlogList);
        return Response.success(map);
    }
    
}
