package com.swpu.uchain.blog.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.swpu.uchain.blog.dao.UserMapper;
import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.enums.TemplateCodeEnum;
import com.swpu.uchain.blog.exception.GlobalException;
import com.swpu.uchain.blog.form.LoginForm;
import com.swpu.uchain.blog.form.UserInsertForm;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.PhoneCodeKey;
import com.swpu.uchain.blog.security.JwtProperties;
import com.swpu.uchain.blog.security.JwtUserDetailServiceImpl;
import com.swpu.uchain.blog.service.UserService;
import com.swpu.uchain.blog.util.AliyunSmsUtils;
import com.swpu.uchain.blog.util.JwtTokenUtil;
import com.swpu.uchain.blog.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Author hobo
 * @Date 19-4-22 下午7:04
 * @Description
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${file.headPic}")
    private  String headPicPath;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private RedisService redisService;


    @Override
    public boolean insert(User user) {
        return (userMapper.insert(user) == 1);
    }

    @Override
    public User selectByUserId(Long  userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByPhoneNum(String phoneNumber) {
        return userMapper.getUserByPhone(phoneNumber);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNum = authentication.getName();
        String key = "anonymousUser";
        if (authentication != null && !phoneNum.equals(key)) {
            return getUserByPhoneNum(phoneNum);
        }
        return null;
    }

    @Override
    public Object login(LoginForm loginForm, HttpServletResponse response) {
        User user = userMapper.getUserByPhone(loginForm.getPhoneNum());
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getPhoneNum());

        if (!new BCryptPasswordEncoder().matches(loginForm.getPassword(), userDetails.getPassword())) {
            throw new GlobalException(ResultEnum.PASSWORD_ERROR);
        }

        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getPhoneNum(), loginForm.getPassword(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(), realToken);
        Map map = new HashMap();
        map.put("role", user.getRole());
        map.put("token", realToken);
        return ResultVOUtil.success(map);
    }

    @Override
    public Object insertUser(UserInsertForm userInsertForm) {
        String code = redisService.get(PhoneCodeKey.phoneCodeKey, userInsertForm.getPhoneNumber(), String.class);
        if (!code.equals(userInsertForm.getCode())) {
            throw new GlobalException(ResultEnum.PHONE_CODE_ERROR);
        }
        String password = userInsertForm.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userInsertForm.setPassword(password);
        User user = new User();
        BeanUtils.copyProperties(userInsertForm, user);
        user.setRole(1);
        user.setHeadPortrait(headPicPath);
        if (insert(user)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public Object getValidationCode(String phoneNumber) {
        String code = AliyunSmsUtils.setCode();
        try {
            AliyunSmsUtils.sendInsertUserMsg(phoneNumber, code, TemplateCodeEnum.INSERTUSER.getValue());
            redisService.set(PhoneCodeKey.phoneCodeKey, phoneNumber, code);
            return ResultVOUtil.success(code);
        } catch (ClientException e) {
            log.info("失败原因: {}", e.getMessage());
            return ResultVOUtil.error(ResultEnum.PHONE_CODE_SEND_ERROR);
        }
    }

}

