package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class UpdateUserForm {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("真实姓名")
    private String trueName;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("头像图片地址")
    private String headPortrait;

}
