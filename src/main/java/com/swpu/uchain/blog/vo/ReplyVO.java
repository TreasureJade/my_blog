package com.swpu.uchain.blog.vo;

import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class ReplyVO {

    private Long commentId;

    private Long userId;

    private String userName;

    private String headPortrait;

    private Long replyUserId;

    private String replyUserName;

    private String creatTime;

    private String commentMsg;

}
