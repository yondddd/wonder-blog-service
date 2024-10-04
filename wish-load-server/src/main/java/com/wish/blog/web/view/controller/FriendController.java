package com.wish.blog.web.view.controller;

import com.wish.blog.entity.FriendDO;
import com.wish.blog.service.FriendService;
import com.wish.blog.web.admin.dto.FriendConfigDTO;
import com.wish.blog.web.view.convert.FriendViewConverter;
import com.wish.blog.web.view.req.FriendClickReq;
import com.wish.blog.web.view.vo.FriendViewVO;
import com.wish.common.annotation.VisitLogger;
import com.wish.common.enums.VisitBehavior;
import com.wish.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
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
