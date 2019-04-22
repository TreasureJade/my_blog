package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.form.LoginForm;
import com.swpu.uchain.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Author hobo
 * @Date 19-4-22 下午10:06
 * @Description
 **/
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(name = "用户登录", value = "/login")
    public Object login(LoginForm loginForm, HttpServletResponse response) {
        return userService.login(loginForm, response);
    }

}
