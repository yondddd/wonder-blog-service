package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.UserDO;
import com.yond.blog.service.UserService;
import com.yond.blog.web.handler.session.UserSession;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 账号后台管理
 * @Author: Naccl
 * @Date: 2023-01-31
 */
@RestController
@RequestMapping("/admin")
public class AccountAdminController {

    @Resource
    private UserService userService;

    /**
     * 账号密码修改
     */
    @PostMapping("/account")
    public Response<Boolean> account(@RequestBody UserDO user,
                                     UserSession userSession) {
        boolean res = userService.changeAccount(user, userSession);
        return res ? Response.success() : Response.fail("修改失败");
    }

}
