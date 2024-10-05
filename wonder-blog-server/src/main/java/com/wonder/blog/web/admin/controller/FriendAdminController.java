package com.wonder.blog.web.admin.controller;

import com.wonder.blog.entity.FriendDO;
import com.wonder.blog.service.FriendService;
import com.wonder.blog.service.SiteConfigService;
import com.wonder.blog.web.admin.convert.FriendConverter;
import com.wonder.blog.web.admin.dto.FriendConfigDTO;
import com.wonder.blog.web.admin.req.*;
import com.wonder.blog.web.admin.vo.FriendConfigVO;
import com.wonder.blog.web.admin.vo.FriendVO;
import com.wonder.common.annotation.OperationLogger;
import com.wonder.common.constant.SiteConfigConstant;
import com.wonder.common.enums.EnableStatusEnum;
import com.wonder.common.resp.PageResponse;
import com.wonder.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 友链页面后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/friend")
public class FriendAdminController {
    
    @Resource
    private FriendService friendService;
    @Resource
    private SiteConfigService siteConfigService;
    
    @PostMapping("/page")
    public PageResponse<List<FriendVO>> page(@RequestBody FriendPageReq req) {
        Pair<Integer, List<FriendDO>> pair = friendService.page(req.getPageNo(), req.getPageSize());
        List<FriendVO> data = pair.getRight().stream().map(FriendConverter::do2vo).toList();
        return PageResponse.<List<FriendVO>>custom().setData(data).setTotal(pair.getLeft()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }
    
    @OperationLogger("更新友链公开状态")
    @PostMapping("/published")
    public Response<Boolean> published(@RequestBody FriendPublishedReq req) {
        FriendDO update = FriendDO.custom()
                .setId(req.getId())
                .setPublished(req.getPublished());
        friendService.updateSelective(update);
        return Response.success();
    }
    
    @OperationLogger("添加友链")
    @PostMapping("/add")
    public Response<Boolean> add(@RequestBody FriendAddReq req) {
        FriendDO insert = FriendDO.custom()
                .setNickname(req.getNickname())
                .setDescription(req.getDescription())
                .setWebsite(req.getWebsite())
                .setAvatar(req.getAvatar())
                .setPublished(req.getPublished());
        friendService.insertSelective(insert);
        return Response.success();
    }
    
    @OperationLogger("更新友链")
    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody FriendUpdateReq req) {
        FriendDO update = FriendDO.custom()
                .setId(req.getId())
                .setNickname(req.getNickname())
                .setDescription(req.getDescription())
                .setWebsite(req.getWebsite())
                .setAvatar(req.getAvatar())
                .setPublished(req.getPublished());
        friendService.updateSelective(update);
        return Response.success();
    }
    
    @OperationLogger("删除友链")
    @PostMapping("/del")
    public Response<Boolean> del(@RequestBody FriendDelReq req) {
        FriendDO del = FriendDO.custom()
                .setId(req.getId())
                .setStatus(EnableStatusEnum.DELETE.getVal());
        friendService.updateSelective(del);
        return Response.success();
    }
    
    @PostMapping("/getConfig")
    public Response<FriendConfigVO> friendConfig() {
        FriendConfigDTO friendConfig = friendService.getFriendConfig();
        FriendConfigVO data = new FriendConfigVO();
        data.setContent(friendConfig.getContent());
        data.setCommentEnabled(friendConfig.getCommentEnabled());
        return Response.success(data);
    }
    
    @OperationLogger("修改友链页面配置")
    @PostMapping("/updateConfig")
    public Response<Boolean> updateFriendConfig(@RequestBody FriendUpdateConfigReq req) {
        siteConfigService.updateValue(SiteConfigConstant.FRIEND_CONTENT, req.getContent());
        siteConfigService.updateValue(SiteConfigConstant.FRIEND_COMMENT_ENABLED, String.valueOf(BooleanUtils.toInteger(req.getCommentEnabled())));
        return Response.success();
    }
    
}
