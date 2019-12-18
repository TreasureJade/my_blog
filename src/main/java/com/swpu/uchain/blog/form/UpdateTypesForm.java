package com.swpu.uchain.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class UpdateTypesForm {

    @ApiModelProperty("分类id")
    private Integer id;

    @ApiModelProperty("分类")
    private String typeMsg;

}
