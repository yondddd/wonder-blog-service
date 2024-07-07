package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.FriendDO;
import com.yond.blog.service.FriendService;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: 友链页面后台管理
 * @Author: Naccl
 * @Date: 2020-09-08
 */
@RestController
@RequestMapping("/admin")
public class FriendAdminController {
    @Autowired
    FriendService friendService;

    /**
     * 分页获取友链列表
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/friends")
    public Response friends(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = "create_time asc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<FriendDO> pageInfo = new PageInfo<>(friendService.getFriendList());
        return Response.success(pageInfo);
    }

    /**
     * 更新友链公开状态
     *
     * @param id        友链id
     * @param published 是否公开
     * @return
     */
    @OperationLogger("更新友链公开状态")
    @PutMapping("/friend/published")
    public Response updatePublished(@RequestParam Long id, @RequestParam Boolean published) {
        friendService.updateFriendPublishedById(id, published);
        return Response.ok("操作成功");
    }

    /**
     * 添加友链
     *
     * @param friend 友链DTO
     * @return
     */
    @OperationLogger("添加友链")
    @PostMapping("/friend")
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
