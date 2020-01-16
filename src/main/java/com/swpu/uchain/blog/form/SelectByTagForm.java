package com.swpu.uchain.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class SelectByTagForm {

    @ApiModelProperty("标签id")
    private Integer tagId;

}
