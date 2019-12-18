package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.UserLikes;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLikesMapper {
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("blogId") Long blogId);

    int insert(UserLikes record);

    UserLikes selectByPrimaryKey(@Param("userId") Long userId, @Param("blogId") Long blogId);

    List<UserLikes> selectAll();

    int updateByPrimaryKey(UserLikes record);
}