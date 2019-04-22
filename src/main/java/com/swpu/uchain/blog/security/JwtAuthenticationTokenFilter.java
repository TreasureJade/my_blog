package com.swpu.uchain.blog.security;

import com.swpu.uchain.blog.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtAuthenticationTokenFilter
 * @Author hobo
 * @Date 19-4-22 下午7:36
 * @Description
 **/
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenHeader = "Authorization";


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            log.info("浏览器的请求预处理");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token,Cookie");
            return;
        } else {
            String requestUrl = httpServletRequest.getRequestURI();
            log.info("requestURI: {}", requestUrl);
            String authtoken = httpServletRequest.getHeader(this.tokenHeader);
            String phoneNum = jwtTokenUtil.getUsernameFromToken(authtoken);

            log.info("checking authentication for user " + phoneNum);

            if (phoneNum != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("token中的username不为空，Context中的authentication为空时,进行token验证");

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(phoneNum);
                log.info("加载UserDetails:{}", userDetails.getUsername());
                if (jwtTokenUtil.validateToken(authtoken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    log.info("authenticated user" + phoneNum + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
