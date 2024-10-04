package com.wish.blog.web.admin.controller;

import com.wish.blog.entity.BlogTagDO;
import com.wish.blog.entity.TagDO;
import com.wish.blog.service.BlogService;
import com.wish.blog.service.BlogTagService;
import com.wish.blog.service.TagService;
import com.wish.blog.web.admin.convert.TagConvert;
import com.wish.blog.web.admin.req.TagDelReq;
import com.wish.blog.web.admin.req.TagPageReq;
import com.wish.blog.web.admin.vo.TagVO;
import com.wish.common.annotation.OperationLogger;
import com.wish.common.resp.PageResponse;
import com.wish.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 博客标签后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/tag")
public class TagAdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private TagService tagService;
    @Resource
    private BlogTagService blogTagService;

    @PostMapping("listAll")
    public Response<List<TagVO>> listAll() {
        List<TagVO> data = tagService.listAll().stream()
                .map(TagConvert::do2vo).toList();
        return Response.success(data);
    }

    @PostMapping("/page")
    public PageResponse<List<TagVO>> page(@RequestBody TagPageReq req) {
        Pair<Integer, List<TagDO>> pair = tagService.page(req.getPageNo(), req.getPageSize());
        List<TagVO> data = pair.getRight().stream().map(TagConvert::do2vo).toList();
        return PageResponse.<List<TagVO>>custom().setData(data).setTotal(pair.getLeft()).setSuccess();
    }

    @OperationLogger("添加标签")
    @PostMapping("/save")
    public Response<Boolean> save(@RequestBody TagVO tag) {
        TagDO exist = tagService.getByName(tag.getName());
        Assert.isNull(exist, "标签名称已存在");
        TagDO insert = TagConvert.vo2do(tag);
        tagService.insertSelective(insert);
        return Response.success();
    }

    @OperationLogger("修改标签")
    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody TagVO tag) {
        TagDO exist = tagService.getByName(tag.getName());
        if (exist != null) {
            Assert.isTrue(exist.getId().equals(tag.getId()), "标签名称已存在");
        }
        TagDO update = TagConvert.vo2do(tag);
        tagService.updateSelective(update);
        return Response.success();
    }

    @OperationLogger("删除标签")
    @PostMapping("/del")
    public Response<Boolean> delete(@RequestBody TagDelReq req) {
        List<BlogTagDO> ref = blogTagService.listByTagId(req.getId());
        if (CollectionUtils.isNotEmpty(ref)) {
            return Response.<Boolean>custom().setFailure("已有博客与此标签关联，不可删除");
        }
        tagService.deleteById(req.getId());
        return Response.success();
    }
}
