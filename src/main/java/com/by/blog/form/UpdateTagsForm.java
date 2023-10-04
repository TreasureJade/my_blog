package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class UpdateTagsForm {

    @ApiModelProperty("标签id")
    private Integer id;

    @ApiModelProperty("标签")
    private String tagsMsg;

}
