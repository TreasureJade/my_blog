package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.Comment;
import com.swpu.uchain.blog.vo.CommentVO;
import com.swpu.uchain.blog.vo.ReplyVO;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    /**
     * 获取此博客下所有的父级评论Id
     * @param blogId
     * @return java.util.List<java.lang.Long>
     */
    List<Long> getCommentIdByBlogId(Long blogId);


    List<ReplyVO> selectByPid(Long pid);

    /**
     * 获取评论详情
     * @param id
     * @return
     */
    CommentVO selectCommentById(Long id);
}