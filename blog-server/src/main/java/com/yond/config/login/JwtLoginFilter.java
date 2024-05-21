package com.yond.config.login;

import com.yond.common.exception.BadRequestException;
import com.yond.common.resp.Result;
import com.yond.entity.LoginLog;
import com.yond.entity.User;
import com.yond.service.LoginLogService;
import com.yond.util.IpAddressUtils;
import com.yond.util.JacksonUtils;
import com.yond.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: JWT登录过滤器
 * @Author: Naccl
 * @Date: 2020-07-21
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final LoginLogService loginLogService;
    private final ThreadLocal<String> currentUsername = new ThreadLocal<>();

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, LoginLogService loginLogService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
        this.loginLogService = loginLogService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        try {
            if (!HttpMethod.POST.name().equals(request.getMethod())) {
                throw new BadRequestException("请求方法错误");
            }
            User user = JacksonUtils.readValue(request.getInputStream(), User.class);
            currentUsername.set(user.getUsername());
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadRequestException exception) {
            response.setContentType("application/json;charset=utf-8");
            Result result = Result.create(400, "非法请求");
            PrintWriter out = response.getWriter();
            out.write(JacksonUtils.writeValueAsString(result));
            out.flush();
            out.close();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        String jwt = JwtUtils.generateToken(authResult.getName(), authResult.getAuthorities());
        response.setContentType("application/json;charset=utf-8");
        User user = (User) authResult.getPrincipal();
        user.setPassword(null);
        Map<String, Object> map = new HashMap<>(4);
        map.put("user", user);
        map.put("token", jwt);
        Result<Map<String, Object>> result = Result.success(map);
        PrintWriter out = response.getWriter();
        out.write(JacksonUtils.writeValueAsString(result));
        out.flush();
        out.close();
        LoginLog log = handleLog(request, true, "登录成功");
        loginLogService.saveLoginLog(log);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String msg = exception.getMessage();
        if (exception instanceof LockedException) {
            msg = "账号被锁定";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "密码过期";
        } else if (exception instanceof AccountExpiredException) {
            msg = "账号过期";
        } else if (exception instanceof DisabledException) {
            msg = "账号被禁用";
        } else if (exception instanceof BadCredentialsException) {
            msg = "用户名或密码错误";
        }
        PrintWriter out = response.getWriter();
        out.write(JacksonUtils.writeValueAsString(Result.create(401, msg)));
        out.flush();
        out.close();
        LoginLog log = handleLog(request, false, StringUtils.substring(msg, 0, 50));
        loginLogService.saveLoginLog(log);
    }

    private LoginLog handleLog(HttpServletRequest request, boolean status, String description) {
        String username = currentUsername.get();
        currentUsername.remove();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        return new LoginLog(username, ip, status, description, userAgent);
    }
}
