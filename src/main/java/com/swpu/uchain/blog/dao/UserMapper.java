package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.vo.UserVO;

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