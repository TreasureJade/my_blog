package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * @description
 */
@Data
public class UpdatePwForm {

    @NotNull(message = "手机号码不能为空")
    @ApiModelProperty("手机号码")
    private String phoneNum;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String code;


    @NotNull(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String newPassword;

}
