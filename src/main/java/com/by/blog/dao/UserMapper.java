package com.by.blog.dao;

import com.by.blog.entity.User;
import com.by.blog.vo.UserVO;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User getUserByPhone(String phoneNum);

    UserVO selectByPhoneNum(String phoneNumber);
}