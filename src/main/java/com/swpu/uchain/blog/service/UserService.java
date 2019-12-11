package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.form.LoginForm;
import com.swpu.uchain.blog.form.UpdatePwForm;
import com.swpu.uchain.blog.form.UpdateUserForm;
import com.swpu.uchain.blog.form.UserInsertForm;
import com.swpu.uchain.blog.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户服务接口
 *
 * @author Hobo
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user
     * @return boolean
     */
    boolean insert(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return boolean
     */
    boolean update(User user);

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    User selectByUserId(Long userId);

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
     *
     * @param loginForm
     * @param response
     * @return java.lang.Object
     * @author hobo
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response);

    /**
     * 用户注册
     *
     * @param userInsertForm
     * @return java.lang.Object
     */
    ResultVO insertUser(UserInsertForm userInsertForm);

    /**
     * @param phoneNumber
     * @return java.lang.Object
     */
    ResultVO getValidationCode(String phoneNumber);

    /***
     * 修改密码
     * @param form
     * @return java.lang.Object
     */
    ResultVO updatePw(UpdatePwForm form);

    /**
     * 用户查看个人信息
     * @return
     */
    ResultVO getOwnerMsg();

    /**
     * 用户更新个人信息
     * @param form
     * @return
     */
    ResultVO updateUser(UpdateUserForm form);

    /**
     * 用户上传头像图片
     * @param file 头像图片
     * @return
     */
    ResultVO uploadHeadPic(MultipartFile file);
}
