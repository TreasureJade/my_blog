package com.swpu.uchain.blog.enums;

import lombok.Getter;

/**
 * @ClassName ResultEnum
 * @Author hobo
 * @Date 19-4-22 下午3:45
 * @Description
 **/
@Getter
public enum ResultEnum {

    USER_NOT_EXIST(1, "用户不存在"),
    PASSWORD_ERROR(2, "用户名错误"),
    PHONE_CODE_ERROR(3, "手机验证码错误"),
    PHONE_CODE_SEND_ERROR(4, "手机验证码发送失败"),


    ARTICLE_TITLE_EXIST(10, "文章标题已经存在"),
    ARTICLE_NOT_EXIST(11, "文章不存在"),
    ADD_READINGS_ERROR(12, "阅读量信息添加失败"),


    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式"),


    USER_NOT_LOGIN(13,"用户未登录");
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
