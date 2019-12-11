package com.swpu.uchain.blog.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.swpu.uchain.blog.dao.UserMapper;
import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.enums.TemplateCodeEnum;
import com.swpu.uchain.blog.exception.GlobalException;
import com.swpu.uchain.blog.form.LoginForm;
import com.swpu.uchain.blog.form.UpdatePwForm;
import com.swpu.uchain.blog.form.UpdateUserForm;
import com.swpu.uchain.blog.form.UserInsertForm;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.PhoneCodeKey;
import com.swpu.uchain.blog.security.JwtProperties;
import com.swpu.uchain.blog.security.JwtUserDetailServiceImpl;
import com.swpu.uchain.blog.service.UserService;
import com.swpu.uchain.blog.util.*;
import com.swpu.uchain.blog.vo.ResultVO;
import com.swpu.uchain.blog.vo.UserVO;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Value("${file.head-pic.default-pic}")
    private String headDefaultPicPath;

    @Value("${file.head-pic.upload-pic}")
    private String headPicUploadPath;

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
    public boolean update(User user) {
        return userMapper.updateByPrimaryKey(user) == 1;
    }

    @Override
    public User selectByUserId(Long userId) {
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
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
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
    public ResultVO insertUser(UserInsertForm userInsertForm) {
        if (userMapper.selectByPhoneNum(userInsertForm.getPhoneNumber()) != null) {
            return ResultVOUtil.error(ResultEnum.USER_ALREADY_EXIST);
        }
        String code = redisService.get(PhoneCodeKey.phoneCodeKey, userInsertForm.getPhoneNumber(), String.class);
        if (code == null) {
            return ResultVOUtil.error(ResultEnum.CODE_IS_NULL);
        }
        if (!code.equals(userInsertForm.getCode())) {
            return ResultVOUtil.error(ResultEnum.PHONE_CODE_ERROR);
        }
        String password = userInsertForm.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userInsertForm.setPassword(password);
        User user = new User();
        BeanUtils.copyProperties(userInsertForm, user);
        user.setRole(1);
        user.setHeadPortrait(headDefaultPicPath);
        if (insert(user)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO getValidationCode(String phoneNumber) {
        String code = RandomUtil.setCode();
        try {
            if (AliyunSmsUtils.sendInsertUserMsg(phoneNumber, code, TemplateCodeEnum.INSERTUSER.getValue())) {
                redisService.set(PhoneCodeKey.phoneCodeKey, phoneNumber, code);
                return ResultVOUtil.success(code);
            }
        } catch (ClientException e) {
            log.info("失败原因: {}", e.getMessage());
            return ResultVOUtil.error(ResultEnum.PHONE_CODE_SEND_ERROR);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO updatePw(UpdatePwForm form) {
        User user = userMapper.getUserByPhone(form.getPhoneNum());
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        String code = redisService.get(PhoneCodeKey.phoneCodeKey, form.getPhoneNum(), String.class);
        if (code == null) {
            return ResultVOUtil.error(ResultEnum.CODE_IS_NULL);
        }
        if (!code.equals(form.getCode())) {
            return ResultVOUtil.error(ResultEnum.PHONE_CODE_ERROR);
        }
        user.setPassword(form.getNewPassword());
        if (update(user)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO getOwnerMsg() {
        User user = getCurrentUser();
        UserVO result = userMapper.selectByPhoneNum(user.getPhoneNumber());
        if (result != null) {
            return ResultVOUtil.success(result);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO updateUser(UpdateUserForm form) {
        User user = getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        BeanUtils.copyProperties(form, user);
        user.setUserId(user.getUserId());
        if (update(user)) {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            return ResultVOUtil.success(vo);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO uploadHeadPic(MultipartFile file) {
        User user = getCurrentUser();
        if (file != null) {
            // 获得文件后缀
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String filePath = UploadFileUtil.uploadFile(headPicUploadPath + user.getUserId() + "."
                    + suffix, file);
            log.info("filePath:{}", filePath);
            return ResultVOUtil.success(filePath);
        }
        return ResultVOUtil.error(ResultEnum.FILE_UPLOAD_ERROR);
    }

}

