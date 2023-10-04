package com.by.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class PageForm {

    @ApiModelProperty("页数")
    private int pageNum;

    @ApiModelProperty("一页展示的个数")
    private int pageSize;

}
