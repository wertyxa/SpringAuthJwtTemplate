package com.wertyxa.springauthjwttemplate.core.web.controllers;

import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import com.wertyxa.springauthjwttemplate.core.dao.entity.UserRole;
import com.wertyxa.springauthjwttemplate.core.service.UserService;
import com.wertyxa.springauthjwttemplate.core.utils.RequestUser;
import com.wertyxa.springauthjwttemplate.core.utils.ResponseWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.security.Principal;

@RestControllerAdvice(basePackages = "com.wertyxa.springauthjwttemplate.core")
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private final UserService userService;

    public CustomResponseBodyAdvice(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !(
                returnType.getParameterType().equals(ResponseEntity.class)
                        || returnType.getParameterType().equals(byte[].class)
                        || returnType.getParameterType().equals(void.class)
        );
    }

    @Override
    public ResponseWrapper beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response
            ) {
        User user = getUserFromPrincipal(request.getPrincipal());
        return new ResponseWrapper(true, body, new RequestUser(user.getId(), user.getUsername(), user.getRoles().stream().map(UserRole::getName).toList()), request.getURI().getPath());
    }

    private User getUserFromPrincipal(Principal principal) {
        if (principal == null) return User.builder().id(0L).username("Unauthorized").build();
        return userService.get((String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
    }
}