package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.accessctro.RoleContro;
import com.swpu.uchain.blog.enums.RoleEnum;
import com.swpu.uchain.blog.form.UpdatePwForm;
import com.swpu.uchain.blog.form.UpdateUserForm;
import com.swpu.uchain.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;


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

    @ApiOperation("用户修改密码")
    @PostMapping(name = "用户修改密码", value = "/updatePw")
    public Object updatePw(UpdatePwForm form) {
        return userService.updatePw(form);
    }

    @ApiOperation("用户查看个人信息")
    @GetMapping(name = "用户查看个人信息", value = "/getOwnerMsg")
    public Object getOwnerMsg() {
        return userService.getOwnerMsg();
    }

    @ApiOperation("修改个人信息")
    @PostMapping(name = "修改个人信息", value = "/updateUser")
    public Object updateUser(UpdateUserForm form,@RequestParam("头像图片") MultipartFile file) {
        return userService.updateUser(form,file);
    }


}
