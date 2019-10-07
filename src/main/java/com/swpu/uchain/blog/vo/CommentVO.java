package com.swpu.uchain.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class CommentVO {

    private Long commentId;

    private String userName;

    private String headPortrait;

    private String creatTime;

    private String commentMsg;

    private List<ReplyVO> replyVO;


}
