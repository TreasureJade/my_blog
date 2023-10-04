package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class InsertTypesForm {

    @ApiModelProperty("分类")
    private String typeMsg;
}
