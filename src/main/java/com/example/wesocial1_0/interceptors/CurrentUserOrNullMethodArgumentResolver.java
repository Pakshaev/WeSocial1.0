package com.example.wesocial1_0.interceptors;

import com.example.wesocial1_0.annotations.CurrentUser;
import com.example.wesocial1_0.configuration.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Service
public class CurrentUserOrNullMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader("Authorization");
        if (!StringUtils.hasLength(authorization) || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return jwtService.extractUsername(authorization.substring(7));
    }
}
