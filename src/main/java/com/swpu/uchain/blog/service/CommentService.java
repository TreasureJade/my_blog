package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.entity.Comment;
import com.swpu.uchain.blog.entity.Reply;
import com.swpu.uchain.blog.vo.ResultVO;

/**
 * @ClassName CommentService
 * @Author hobo
 * @Date 19-5-1 下午4:28
 * @Description 评论回复接口
 **/
public interface CommentService {

    /**
     * 新增评论
     *
     * @param comment
     * @return boolean
     * @author hobo
     */
    boolean insert(Comment comment);

    /***
     * 删除评论
     * @param id
     * @author hobo
     * @return boolean
     */
    boolean delete(Long id);

    /**
     * 回复评论
     *
     * @param reply
     * @return boolean
     * @author hobo
     */
    boolean insertReply(Reply reply);

    /***
     *
     * @param id
     * @author hobo
     * @return
     */
    boolean deleteReply(Long id);

    /**
     * 返回添加评论结果
     *
     * @param comment
     * @return com.swpu.uchain.blog.vo.ResultVO
     * @author hobo
     */
    ResultVO insertComment(Comment comment);

    /**
     * 返回删除评论结果
     *
     * @param id
     * @return com.swpu.uchain.blog.vo.ResultVO
     * @author hobo
     */
    ResultVO deleteComment(Long id);

    /**
     * 获取文章下所有的评论及回复
     *
     * @author hobo
     * @return com.swpu.uchain.blog.vo.ResultVO
     */
    ResultVO getCommentAndReplyList();
}
