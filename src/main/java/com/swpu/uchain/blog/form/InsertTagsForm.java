package com.swpu.uchain.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class InsertTagsForm {

    @ApiModelProperty("标签")
    private String tagsMsg;
}
