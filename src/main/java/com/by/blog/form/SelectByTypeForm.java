package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class SelectByTypeForm {

    @ApiModelProperty("标签id")
    private Integer typeId;

}
