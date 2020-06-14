package com.pbl.backend.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 杜东方
 * @date: 2020/5/23
 * @description: 用来解决匿名用户访问需要权限才能访问的资源时的异常
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String reason = "统一处理，原因：" + authException.getMessage();
        response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
    }
}
