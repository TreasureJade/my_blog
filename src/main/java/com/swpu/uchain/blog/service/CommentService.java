package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.entity.Comment;
import com.swpu.uchain.blog.form.CreatCommentForm;
import com.swpu.uchain.blog.vo.CommentVO;
import com.swpu.uchain.blog.vo.ResultVO;

import java.util.List;

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
     * @return
     */
    ResultVO creatComment(CreatCommentForm form);

    /**
     * 获取文章下所有评论
     * @param blogId
     * @return
     */
    List<CommentVO> getAllCommentByBlogId(Long blogId);
}
