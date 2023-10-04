package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * @description
 */
@Data
public class CreatCommentForm {

    @ApiModelProperty("博客Id")
    @NotNull(message = "博客id不能为空")
    private Long blogId;

    @ApiModelProperty("评论内容")
    @NotNull(message = "评论内容不能为空")
    private String commentMsg;

    @ApiModelProperty("被回复人 ")
    private Long replyUserId;

    @ApiModelProperty("父级评论Id 若为首级评论则为0")
    @NotNull(message = "父级评论Id不能为空")
    private Long pid;


}
