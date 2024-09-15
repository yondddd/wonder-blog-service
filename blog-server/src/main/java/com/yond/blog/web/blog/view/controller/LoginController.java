package com.yond.blog.web.blog.view.controller;

import com.yond.blog.entity.UserDO;
import com.yond.blog.service.UserService;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.util.jwt.PayloadHelper;
import com.yond.blog.web.blog.view.dto.LoginInfo;
import com.yond.common.constant.JwtConstant;
import com.yond.common.enums.RoleEnum;
import com.yond.common.resp.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前台登录
 * @Author: Yond
 */
@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录成功后，签发博主身份Token
     */
    @PostMapping("/view/login")
    public Response<Map<String, Object>> login(@RequestBody LoginInfo loginInfo) {
        UserDO user = userService.getByNameAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
        if (!RoleEnum.ADMIN.getVal().equals(user.getRole())) {
            return Response.custom(403, "无权限");
        }
        user.setPassword(null);

        PayloadHelper payloadHelper = new PayloadHelper()
                .setIssuer(JwtConstant.DEFAULT_CLIENT)
                .setIssuedAt(new Date())
                .setSubject(JwtConstant.ADMIN_PREFIX + user.getUsername())
                .setSecret(JwtConstant.DEFAULT_SECRET)
                .setAdditionalInfo(new HashMap<>());
        String token = JwtUtil.creatToken(payloadHelper, JwtConstant.LOGIN_TIME);

        Map<String, Object> map = new HashMap<>(4);
        map.put("user", user);
        map.put("token", token);
        return Response.success(map);
    }

}
