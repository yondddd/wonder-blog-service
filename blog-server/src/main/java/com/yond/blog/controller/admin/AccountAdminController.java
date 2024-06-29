package com.yond.blog.controller.admin;

import com.yond.common.resp.Result;
import com.yond.blog.entity.User;
import com.yond.blog.service.UserService;
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
    public Result account(@RequestBody User user, @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
        boolean res = userService.changeAccount(user, jwt);
        return res ? Result.ok("修改成功") : Result.failure("修改失败");
    }
}
