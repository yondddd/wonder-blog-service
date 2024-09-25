package com.yond.blog.web.view.controller;

import com.yond.blog.entity.FriendDO;
import com.yond.blog.service.FriendService;
import com.yond.blog.web.admin.dto.FriendConfigDTO;
import com.yond.blog.web.view.convert.FriendViewConverter;
import com.yond.blog.web.view.req.FriendClickReq;
import com.yond.blog.web.view.vo.FriendViewVO;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 友链
 * @Author: Yond
 */
@RestController
@RequestMapping("/view/friend")
public class FriendController {
    
    @Resource
    private FriendService friendService;
    
    @VisitLogger(VisitBehavior.FRIEND)
    @PostMapping("/list")
    public Response<FriendViewVO> friends() {
        FriendViewVO data = new FriendViewVO();
        List<FriendDO> list = new ArrayList<>(friendService.listAll().stream().filter(FriendDO::getPublished).toList());
        // 打乱
        Collections.shuffle(list);
        FriendConfigDTO friendConfig = friendService.getFriendConfig();
        data.setContent(friendConfig.getContent());
        data.setCommentEnabled(friendConfig.getCommentEnabled());
        data.setFriendList(list.stream().map(FriendViewConverter::do2vo).toList());
        return Response.success(data);
    }
    
    @VisitLogger(VisitBehavior.CLICK_FRIEND)
    @PostMapping("/friendClick")
    public Response<Boolean> friendClick(@RequestBody FriendClickReq req) {
        friendService.incrViewById(req.getId());
        return Response.success();
    }
    
}
