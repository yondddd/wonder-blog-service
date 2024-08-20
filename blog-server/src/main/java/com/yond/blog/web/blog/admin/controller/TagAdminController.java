package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.TagDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.blog.admin.convert.TagConvert;
import com.yond.blog.web.blog.admin.vo.TagVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 博客标签后台管理
 * @Author: Naccl
 * @Date: 2020-08-02
 */
@RestController
@RequestMapping("/admin/tag")
public class TagAdminController {

    private final BlogService blogService;
    private final TagService tagService;

    public TagAdminController(BlogService blogService, TagService tagService) {
        this.blogService = blogService;
        this.tagService = tagService;
    }

    @PostMapping("listAll")
    public Response<List<TagVO>> listAll() {
        List<TagVO> data = tagService.listAll().stream()
                .map(TagConvert::do2vo).toList();
        return Response.success(data);
    }

    /**
     * 获取博客标签列表
     *
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/tags")
    public Response tags(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<TagDO> pageInfo = new PageInfo<>(tagService.getTagList());
        return Response.ok("请求成功", pageInfo);
    }

    /**
     * 添加新标签
     *
     * @param tag 标签实体
     * @return
     */
    @OperationLogger("添加标签")
    @PostMapping("/tag")
    public Response saveTag(@RequestBody TagDO tag) {
        return getResult(tag, "save");
    }

    /**
     * 修改标签
     *
     * @param tag 标签实体
     * @return
     */
    @OperationLogger("修改标签")
    @PutMapping("/tag")
    public Response updateTag(@RequestBody TagDO tag) {
        return getResult(tag, "update");
    }

    /**
     * 执行标签添加或更新操作：校验参数是否合法，标签是否已存在
     *
     * @param tag  标签实体
     * @param type 添加或更新
     * @return
     */
    private Response getResult(TagDO tag, String type) {
        if (StringUtils.isBlank(tag.getName())) {
            return Response.failure("参数不能为空");
        }
        //查询标签是否已存在
        TagDO tag1 = tagService.getTagByName(tag.getName());
        //如果 tag1.getId().equals(tag.getId()) == true 就是更新标签
        if (tag1 != null && !tag1.getId().equals(tag.getId())) {
            return Response.failure("该标签已存在");
        }
        if ("save".equals(type)) {
            tagService.saveTag(tag);
            return Response.ok("添加成功");
        } else {
            tagService.updateTag(tag);
            return Response.ok("更新成功");
        }
    }

    /**
     * 按id删除标签
     *
     * @param id 标签id
     * @return
     */
    @OperationLogger("删除标签")
    @DeleteMapping("/tag")
    public Response delete(@RequestParam Long id) {
        //删除存在博客关联的标签后，该博客的查询会出异常
        int num = blogService.countBlogByTagId(id);
        if (num != 0) {
            return Response.failure("已有博客与此标签关联，不可删除");
        }
        tagService.deleteTagById(id);
        return Response.ok("删除成功");
    }
}
