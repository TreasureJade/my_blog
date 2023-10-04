package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hobo
 * @description
 */
@Data
public class CreatArticleForm {

    @ApiModelProperty("摘要")
    @NotNull(message = "摘要不能为空")
    private String digest;

    @ApiModelProperty("作者")
    @NotNull(message = "作者不能为空")
    private String author;

    @ApiModelProperty("标题")
    @NotNull(message = "标题不能为空")
    private String title;

    @ApiModelProperty("标签Id")
    @NotNull(message = "标签Id不能为空")
    private Integer tagsId;

    @ApiModelProperty("分类Id")
    @NotNull(message = "分类Id不能为空")
    private Integer typeId;

    @ApiModelProperty("文章内容  html文本")
    @NotNull(message = "文章内容不能为空")
    private String article;
}
