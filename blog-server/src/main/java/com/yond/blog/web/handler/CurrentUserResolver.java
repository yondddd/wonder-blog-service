package com.yond.blog.web.handler;

import com.yond.blog.entity.UserDO;
import com.yond.blog.service.UserService;
import com.yond.blog.web.handler.session.UserSession;
import com.yond.common.constant.JwtConstant;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {
    
    @Resource
    private UserService userService;
    
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return this.userService != null && UserSession.class.isAssignableFrom(methodParameter.getParameterType());
    }
    
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String guid = (String) request.getAttribute(JwtConstant.GUID_HEADER);
        if (StringUtils.isNotBlank(guid)) {
            UserDO user = userService.getByGuid(guid);
            return UserSession.custom()
                    .setUserId(user.getId())
                    .setGuid(user.getGuid())
                    .setUserName(user.getUsername());
        }
        return null;
    }
    
}
