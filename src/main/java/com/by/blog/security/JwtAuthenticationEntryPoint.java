package com.by.blog.security;

import com.by.blog.enums.ResultEnum;
import com.by.blog.util.ResultVOUtil;
import com.by.blog.vo.ResultVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @ClassName JwtAuthenticationEntryPoint
 * @Author hobo
 * @Date 19-4-22 下午7:19
 * @Description 拦截器
 **/
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access_Control_Allow_Origin", "*");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        ResultVO result = ResultVOUtil.error(ResultEnum.AUTHENTICATION_ERROR);
        log.info("需要身份认证:{}", result);
        Gson gson = new Gson();
        httpServletResponse.getWriter().append(gson.toJson(result));
    }
}
