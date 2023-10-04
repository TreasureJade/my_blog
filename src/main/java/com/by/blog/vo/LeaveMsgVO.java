package com.by.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class LeaveMsgVO {

    private Long leaveMessageId;

    private Long userId;

    private String userName;

    private String headPortrait;

    private String creatTime;

    private String leaveMsg;

    private List<ReplyVO> replyVO;
}
