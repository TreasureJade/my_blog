package com.by.blog.controller;

import com.by.blog.form.LoginForm;
import com.by.blog.form.UserInsertForm;
import com.by.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Author hobo
 * @Date 19-4-22 下午10:06
 * @Description
 **/
@RestController
@RequestMapping("/anon")
@Api(tags = "登录注册")
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping(name = "用户注册", value = "/insert")
    public Object insert(UserInsertForm userInsertForm){
        return userService.insertUser(userInsertForm);
    }

    @ApiOperation("用户登录")
    @PostMapping(name = "用户登录", value = "/login")
    public Object login(LoginForm loginForm, HttpServletResponse response) {
        return userService.login(loginForm, response);
    }

    @ApiOperation("获取验证码")
    @PostMapping(name = "获取验证码",value = "/getCode")
    @ApiImplicitParam(name = "phoneNumber",value = "手机号码")
    public Object getValidationCode(String phoneNumber) {
        return userService.getValidationCode(phoneNumber);
    }

}
