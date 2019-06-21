package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.entity.Comment;
import com.swpu.uchain.blog.vo.ResultVO;

/**
 * @author hobo
 * @description
 */
public interface CommentService {
    /**
     * @param comment
     * @return boolean
     */
    boolean insert(Comment comment);

    /***
     *
     * @param id
     * @return boolean
     */
    boolean delete(Long id);

    /**
     * 评论
     *
     * @param blogId
     * @param commentMsg
     * @return
     */
    ResultVO creatComment(Long blogId, String commentMsg);

    /**
     * 回复评论
     *
     * @param blogId
     * @param commentId
     * @param replyId
     * @param commentMsg
     * @return
     */
    ResultVO replyComment(Long blogId, Long commentId, Long replyId, String commentMsg);
}
