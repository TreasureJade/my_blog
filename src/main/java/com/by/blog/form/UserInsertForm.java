package com.by.blog.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * 用户注册表单
 */
@Data
public class UserInsertForm {


    @NotNull(message = "注册手机号码不能为空")
    @ApiModelProperty("注册手机号码")
    private String phoneNumber;

    @NotNull(message = "注册密码不能为空")
    @ApiModelProperty("注册密码")
    private String password;

    @NotNull(message = "注册用户名不能为空")
    @ApiModelProperty("注册用户名")
    private String username;

    @NotNull(message = "注册验证码不能为空")
    @ApiModelProperty("注册验证码")
    private String code;

}
