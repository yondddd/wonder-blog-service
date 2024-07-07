package com.yond.blog.web.filter;


import com.yond.blog.entity.UserDO;
import com.yond.blog.service.UserService;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.util.web.WebFilterUtil;
import com.yond.blog.web.filter.local.LocalFilterChain;
import com.yond.blog.web.filter.local.LocalHttpContext;
import com.yond.blog.web.filter.local.LocalHttpFilter;
import com.yond.common.constant.JwtConstant;
import com.yond.common.resp.Response;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Date;

/**
 * @author yond
 * @date 6/1/2024
 * @description
 */
public class LocalAuthFilter implements LocalHttpFilter {

    private final UserService userService;

    public LocalAuthFilter(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void doFilter(LocalHttpContext context, LocalFilterChain<LocalHttpContext, IOException, ServletException> chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = context.getRequest();
        HttpServletResponse servletResponse = context.getResponse();
        String servletPath = servletRequest.getServletPath();

        if (HttpMethod.OPTIONS.toString().equals(servletRequest.getMethod())) {
            WebFilterUtil.crossHeader(servletResponse);
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if (!servletPath.startsWith("/admin")) {
            chain.doFilter(context);
            return;
        }

        if (servletPath.contains("login") || servletPath.contains("webTitleSuffix")) {
            chain.doFilter(context);
            return;
        }

        String header = servletRequest.getHeader(JwtConstant.TOKEN_HEADER);
        if (StringUtils.isBlank(header)) {
            WebFilterUtil.returnFail(servletResponse, HttpServletResponse.SC_UNAUTHORIZED, Response.custom(HttpStatus.UNAUTHORIZED.value(), "token不存在"));
            return;
        }
        Claims claims = JwtUtil.validateJwt(header, JwtConstant.DEFAULT_SECRET);
        Date expiration = claims.getExpiration();
        if (expiration.before(new Date())) {
            WebFilterUtil.returnFail(servletResponse, HttpServletResponse.SC_UNAUTHORIZED, Response.custom(HttpStatus.UNAUTHORIZED.value(), "token过期"));
            return;
        }
        String guid = claims.getSubject();
        UserDO userExist = userService.getByGuid(guid);
        if (userExist == null) {
            WebFilterUtil.returnFail(servletResponse, HttpServletResponse.SC_UNAUTHORIZED, Response.custom(HttpStatus.UNAUTHORIZED.value(), "用户不存在"));
            return;
        }
        servletRequest.setAttribute(JwtConstant.GUID_HEADER, guid);
        chain.doFilter(context);
    }

}
