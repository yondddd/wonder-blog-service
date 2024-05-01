package com.yond.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yond.entity.User;
import com.yond.model.vo.Result;
import com.yond.service.UserService;

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
		return res ? Result.ok("修改成功") : Result.error("修改失败");
	}
}
