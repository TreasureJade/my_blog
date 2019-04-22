package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.UserMapper;
import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.exception.GlobalException;
import com.swpu.uchain.blog.form.LoginForm;
import com.swpu.uchain.blog.security.JwtProperties;
import com.swpu.uchain.blog.security.JwtUserDetailServiceImpl;
import com.swpu.uchain.blog.service.UserService;
import com.swpu.uchain.blog.util.JwtTokenUtil;
import com.swpu.uchain.blog.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
