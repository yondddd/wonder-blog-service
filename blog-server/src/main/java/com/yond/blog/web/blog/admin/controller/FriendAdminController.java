package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.FriendDO;
import com.yond.blog.service.FriendService;
import com.yond.blog.web.blog.admin.convert.FriendConverter;
import com.yond.blog.web.blog.admin.req.FriendPageReq;
import com.yond.blog.web.blog.admin.req.FriendPublishedReq;
import com.yond.blog.web.blog.admin.vo.FriendVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 友链页面后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/friend")
public class FriendAdminController {
    
    @Resource
    private FriendService friendService;
    
    @PostMapping("/page")
    public PageResponse<List<FriendVO>> page(@RequestBody FriendPageReq req) {
        Pair<Integer, List<FriendDO>> pair = friendService.page(req.getPageNo(), req.getPageSize());
        List<FriendVO> data = pair.getRight().stream().map(FriendConverter::do2vo).toList();
        return PageResponse.<List<FriendVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }
    
    @OperationLogger("更新友链公开状态")
    @PutMapping("/published")
    public Response<Boolean> published(@RequestBody FriendPublishedReq req) {
        FriendDO update = FriendDO.custom().setId(req.getId())
                .setPublished(req.getPublished());
        friendService.updateSelective(update);
        return Response.success();
    }
    
    @OperationLogger("添加友链")
    @PostMapping("/add")
    public Response saveFriend(@RequestBody FriendDO friend) {
        friendService.saveFriend(friend);
        return Response.ok("添加成功");
    }
    
    /**
     * 更新友链
     *
     * @param friend 友链DTO
     * @return
     */
    @OperationLogger("更新友链")
    @PutMapping("/friend")
    public Response updateFriend(@RequestBody com.yond.blog.web.blog.view.dto.Friend friend) {
        friendService.updateFriend(friend);
        return Response.ok("修改成功");
    }
    
    /**
     * 按id删除友链
     *
     * @param id
     * @return
     */
    @OperationLogger("删除友链")
    @DeleteMapping("/friend")
    public Response deleteFriend(@RequestParam Long id) {
        friendService.deleteFriend(id);
        return Response.ok("删除成功");
    }
    
    /**
     * 获取友链页面信息
     *
     * @return
     */
    @GetMapping("/friendInfo")
    public Response friendInfo() {
        return Response.ok("请求成功", friendService.getFriendInfo(false, false));
    }
    
    /**
     * 修改友链页面评论开放状态
     *
     * @param commentEnabled 是否开放评论
     * @return
     */
    @OperationLogger("修改友链页面评论开放状态")
    @PutMapping("/friendInfo/commentEnabled")
    public Response updateFriendInfoCommentEnabled(@RequestParam Boolean commentEnabled) {
        friendService.updateFriendInfoCommentEnabled(commentEnabled);
        return Response.ok("修改成功");
    }
    
    /**
     * 修改友链页面content
     *
     * @param map 包含content的JSON对象
     * @return
     */
    @OperationLogger("修改友链页面信息")
    @PutMapping("/friendInfo/content")
    public Response updateFriendInfoContent(@RequestBody Map map) {
        friendService.updateFriendInfoContent((String) map.get("content"));
        return Response.ok("修改成功");
    }
}
