package com.swpu.uchain.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class SelectByTagForm {

    @ApiModelProperty("页数")
    private int pageNum;

    @ApiModelProperty("一页展示的个数")
    private int pageSize;

    @ApiModelProperty("标签id")
    private Integer tagId;

}
