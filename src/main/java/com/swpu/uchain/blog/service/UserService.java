package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.form.LoginForm;
import com.swpu.uchain.blog.form.UserInsertForm;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户服务接口
 *
 * @author Hobo
 */
public interface UserService {

    boolean insert(User user);

    /**
     * 根据手机号码查询用户
     *
     * @param phoneNumber
     * @return User
     * @author hobo
     */
    User getUserByPhoneNum(String phoneNumber);

    /**
     * 通过token解析用户
     *
     * @return User
     * @author hobo
     */
    User getCurrentUser();

    /**
     * 用户登录
     * @param loginForm
     * @param response
     * @author hobo
     * @return java.lang.Object
     */
    Object login(LoginForm loginForm, HttpServletResponse response);

    /**
     * 用户注册
     *
     * @param userInsertForm
     * @return java.lang.Object
     */
    Object insertUser(UserInsertForm userInsertForm);

    /**
     *
     * @param phoneNumber
     * @return java.lang.Object
     */
    Object getValidationCode(String phoneNumber);
}
