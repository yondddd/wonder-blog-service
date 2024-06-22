package com.yond.controller.view;

import com.yond.common.resp.Result;
import com.yond.entity.CategoryDO;
import com.yond.entity.Tag;
import com.yond.model.vo.NewBlog;
import com.yond.model.vo.RandomBlog;
import com.yond.service.BlogService;
import com.yond.service.CategoryService;
import com.yond.service.SiteSettingService;
import com.yond.service.TagService;
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
    public Result site() {
        Map<String, Object> map = siteSettingService.getSiteInfo();
        List<NewBlog> newBlogList = blogService.getNewBlogListByIsPublished();
        List<CategoryDO> categoryList = categoryService.listAll();
        List<Tag> tagList = tagService.getTagListNotId();
        List<RandomBlog> randomBlogList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
        map.put("newBlogList", newBlogList);
        map.put("categoryList", categoryList);
        map.put("tagList", tagList);
        map.put("randomBlogList", randomBlogList);
        return Result.ok("请求成功", map);
    }
}
