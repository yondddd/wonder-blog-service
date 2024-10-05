package com.wonder.blog.web.admin.controller;

import com.wonder.blog.entity.LogLoginDO;
import com.wonder.blog.entity.UserDO;
import com.wonder.blog.service.LogLoginService;
import com.wonder.blog.service.UserService;
import com.wonder.blog.util.ip.IpAddressUtils;
import com.wonder.blog.util.jwt.JwtUtil;
import com.wonder.blog.util.jwt.PayloadHelper;
import com.wonder.blog.util.jwt.TokenVO;
import com.wonder.blog.web.admin.req.AccountLoginReq;
import com.wonder.blog.web.admin.req.AccountModifyPwdReq;
import com.wonder.blog.web.handler.session.UserSession;
import com.wonder.common.constant.JwtConstant;
import com.wonder.common.enums.RoleEnum;
import com.wonder.common.resp.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.HashMap;

/**
 * @Description: 博客账号后台管理
 * @Author: Yond
 */
@RestController
@RequestMapping("/admin/account")
public class AccountAdminController {
    
    @Resource
    private UserService userService;
    @Resource
    private LogLoginService logLoginService;
    
    @PostMapping("/modifyPwd")
    public Response<Boolean> modifyPwd(@RequestBody AccountModifyPwdReq req, UserSession userSession) {
        String err = userService.changeAccount(req.getUsername(), req.getPassword(), userSession);
        if (StringUtils.isNotBlank(err)) {
            return Response.fail(err);
        }
        return Response.success();
    }
    
    @PostMapping("/login")
    public Response<TokenVO> login(@RequestBody AccountLoginReq req) {
        UserDO user = userService.getByNameAndPassword(req.getUsername(), req.getPassword());
        if (user == null) {
            logLoginService.saveLoginLog(handleLog(req.getUsername(), false, "用户名或密码错误"));
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
        
        LogLoginDO log = handleLog(user.getUsername(), true, "登录成功");
        logLoginService.saveLoginLog(log);
        
        return Response.success(result);
    }
    
    private LogLoginDO handleLog(String userName, boolean loginSuccess, String description) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        return new LogLoginDO(userName, ip, loginSuccess, description, userAgent);
    }
    
}
