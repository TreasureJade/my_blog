package com.swpu.uchain.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName LoginForm
 * @Author hobo
 * @Date 19-4-22 下午8:46
 * @Description
 **/
@Data
public class LoginForm {


    @NotNull(message = "手机号不能为空")
    @ApiModelProperty("手机号码")
    private String phoneNum;


    @NotNull(message = "密码")
    private String password;

}
