package com.by.blog.dao;

import com.by.blog.entity.UserLikes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLikesMapper {
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("blogId") Long blogId);

    int insert(UserLikes record);

    UserLikes selectByPrimaryKey(@Param("userId") Long userId, @Param("blogId") Long blogId);

    List<UserLikes> selectAll();

    int updateByPrimaryKey(UserLikes record);
}