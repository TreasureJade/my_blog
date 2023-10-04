package com.by.blog.security;

import com.by.blog.accessctro.RoleControl;
import com.by.blog.entity.User;
import com.by.blog.enums.ResultEnum;
import com.by.blog.service.UserService;
import com.by.blog.util.ResultVOUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AuthRoleInterceptor
 * @Author hobo
 * @Date 19-4-22 下午7:47
 * @Description 权限验证
 **/
@Slf4j
@Service
public class AuthRoleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    private static Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String json = gson.toJson(ResultVOUtil.error(ResultEnum.AUTHENTICATION_ERROR));
        User user = userService.getCurrentUser();
        if (user == null) {
            return true;
        }
        log.info("============执行权限验证============");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleControl roleControl = handlerMethod.getMethodAnnotation(RoleControl.class);
            if (roleControl == null) {
                return true;
            }
            Integer roleValue = roleControl.role().getValue();
            Integer userValue = user.getRole();
            log.info("RoleValue:{},userRole:{}", roleValue, userValue);
            if (userValue >= roleValue) {
                return true;
            } else {
                json = gson.toJson(ResultVOUtil.error(ResultEnum.PERMISSION_DENNY));
                log.info("============权限不足===============");
            }
        }
        response.getWriter().append(json);
        return false;
    }
}
