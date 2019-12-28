package com.swpu.uchain.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * @description
 */
@Data
public class CreatLeaveMsgForm {

    @ApiModelProperty("留言内容")
    @NotNull(message = "留言内容不能为空")
    private String leaveMsg;

    @ApiModelProperty("被回复人 ")
    private Long replyUserId;

    @ApiModelProperty("父级留言Id 若为首级评论则为0")
    @NotNull(message = "父级留言Id不能为空")
    private Long pid;

}
