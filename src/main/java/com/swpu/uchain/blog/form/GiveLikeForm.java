package com.swpu.uchain.blog.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class GiveLikeForm {

    @ApiModelProperty("博客id")
    private long blogId;

    @ApiModelProperty("是否点赞 false为取消")
    private Boolean isLike;

}
