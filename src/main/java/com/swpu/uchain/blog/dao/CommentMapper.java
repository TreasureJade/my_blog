package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
    
    List<Comment> selectCommentByPid(Long pid);

    List<Comment> getCommentByBlogIdAndPid(Long blogId);
}