package com.yond.blog.web.view.controller;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.service.SiteConfigService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.view.vo.IndexVO;
import com.yond.blog.web.view.vo.NewBlogVO;
import com.yond.blog.web.view.vo.RandomBlogVO;
import com.yond.common.enums.SiteConfigTypeEnum;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
     */
    @GetMapping("/view/site")
    public Response<IndexVO> site() {
        IndexVO data = new IndexVO();
        Map<String, String> map = new HashMap<>();
        List<SiteConfigDO> all = siteConfigService.listAll();
        for (SiteConfigDO c : all) {
            if (!SiteConfigTypeEnum.THIRD_PARTY_KEY.getVal().equals(c.getType())) {
                map.put(c.getKey(), c.getValue());
            }
        }
        data.setConfig(map);
        List<NewBlogVO> newBlogVOList = blogService.listNewBlog();
        List<RandomBlogVO> randomBlogVOList = blogService.listRandomBlog();
        List<CategoryDO> categoryList = categoryService.listAll();
        List<TagDO> tagList = tagService.listAll();
        data.setNewBlogList(newBlogVOList);
        data.setRandomBlogList(randomBlogVOList);
        data.setCategoryList(categoryList);
        data.setTagList(tagList);
        return Response.success(data);
    }

}
