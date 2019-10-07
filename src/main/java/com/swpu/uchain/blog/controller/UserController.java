package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.accessctro.RoleContro;
import com.swpu.uchain.blog.enums.RoleEnum;
import com.swpu.uchain.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


/**
 * @ClassName UserController
 * @Author hobo
 * @Date 19-4-22 下午9:09
 * @Description
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口 ")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(name = "用户修改密码", value = "/updatePw")
    public void updatePw() {
        //TODO 使用手机验证码实现用户登录
    }

    @RoleContro(role = RoleEnum.USER)
    @PostMapping(name = "修改个人信息", value = "/updateUser")
    public void updateUser() {
        //TODO 用户修改or完善个人信息
    }

    @PostMapping(name = "修改登录手机号码", value = "/updatePhone")
    public void updatePhone() {
        //TODO 使用手机验证码修改登录账号
    }


}
