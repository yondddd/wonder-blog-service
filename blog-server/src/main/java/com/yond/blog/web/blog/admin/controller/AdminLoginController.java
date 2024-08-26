package com.yond.blog.web.blog.admin.controller;

import com.yond.blog.entity.LoginLogDO;
import com.yond.blog.entity.UserDO;
import com.yond.blog.service.LoginLogService;
import com.yond.blog.service.UserService;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.util.jwt.PayloadHelper;
import com.yond.blog.util.jwt.TokenVO;
import com.yond.blog.web.blog.view.dto.LoginInfo;
import com.yond.common.constant.JwtConstant;
import com.yond.common.enums.RoleEnum;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.HashMap;

/**
 * @author yond
 * @date 7/5/2024
 * @description admin login
 */
@RestController
public class AdminLoginController {


    @Resource
    private UserService userService;
    @Resource
    private LoginLogService loginLogService;

    /**
     * 登录成功后，签发博主身份Token
     */
    @PostMapping("/admin/login")
    public Response<TokenVO> login(@RequestBody LoginInfo loginInfo) {
        UserDO user = userService.getByNameAndPassword(loginInfo.getUsername(), loginInfo.getPassword());
        if (user == null) {
            loginLogService.saveLoginLog(handleLog(loginInfo.getUsername(), false, "用户名或密码错误"));
            return Response.fail("用户名或密码错误");
        }
        if (!RoleEnum.ADMIN.getVal().equals(user.getRole())) {
            return Response.custom(403, "无权限");
        }
        user.setPassword(null);

        TokenVO result = new TokenVO();
        PayloadHelper payloadHelper = new PayloadHelper()
                .setIssuer(JwtConstant.DEFAULT_CLIENT)
                .setIssuedAt(new Date())
                .setSubject(user.getGuid())
                .setSecret(JwtConstant.DEFAULT_SECRET)
                .setAdditionalInfo(new HashMap<>());
        String token = JwtUtil.creatToken(payloadHelper, JwtConstant.LOGIN_TIME);
        result.setToken(token);
        result.setUser(user);

        LoginLogDO log = handleLog(user.getUsername(), true, "登录成功");
        loginLogService.saveLoginLog(log);

        return Response.success(result);
    }


    private LoginLogDO handleLog(String userName, boolean loginSuccess, String description) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        return new LoginLogDO(userName, ip, loginSuccess, description, userAgent);
    }

}
