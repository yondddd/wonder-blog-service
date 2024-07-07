package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.UserDO;
import com.yond.blog.service.UserService;
import com.yond.common.constant.JwtConstant;
import com.yond.common.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 账号后台管理
 * @Author: Naccl
 * @Date: 2023-01-31
 */
@RestController
@RequestMapping("/admin")
public class AccountAdminController {
    @Autowired
    UserService userService;

    /**
     * 账号密码修改
     */
    @PostMapping("/account")
    public Response<Boolean> account(@RequestBody UserDO user, @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt) {
        // 校验是当前用户
        boolean res = userService.changeAccount(user, jwt);
        return res ? Response.success() : Response.fail("修改失败");
    }

}
