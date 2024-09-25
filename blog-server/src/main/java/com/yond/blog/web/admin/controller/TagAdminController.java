package com.yond.blog.web.admin.controller;

import com.yond.blog.entity.TagDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.TagService;
import com.yond.blog.web.admin.convert.TagConvert;
import com.yond.blog.web.admin.req.TagDelReq;
import com.yond.blog.web.admin.req.TagPageReq;
import com.yond.blog.web.admin.vo.TagVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
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
        int num = blogService.countByTagId(req.getId());
        if (num != 0) {
            return Response.<Boolean>custom().setFailure("已有博客与此标签关联，不可删除");
        }
        tagService.deleteById(req.getId());
        return Response.success();
    }
}
