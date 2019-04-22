package com.swpu.uchain.blog.security;

import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.enums.RoleEnum;
import com.swpu.uchain.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName JwtUserDetailServiceImpl
 * @Author hobo
 * @Date 19-4-22 下午7:24
 * @Description UserDetailService实现类
 **/
@Service
@Slf4j
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String phoneNum) throws UsernameNotFoundException {
        User user = userService.getUserByPhoneNum(phoneNum);
        if (user == null) {
            log.info("此用户不存在");
            throw new UsernameNotFoundException(String.format("用户名为 %s 的用户不存在", phoneNum));
        } else {
            String role = RoleEnum.getRole(user.getRole());
            return new JwtUser(phoneNum, user.getPassword(), role);
        }
    }
}
