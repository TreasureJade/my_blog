package com.by.blog.enums;

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
    PASSWORD_ERROR(2, "密码错误"),
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


    USER_NOT_LOGIN(13,"用户未登录"),
    FILE_PATH_NOT_EXIST(14,"上传失败，文件目录不存在" ),
    USER_ALREADY_EXIST(15,"此手机已注册" ),
    CODE_IS_NULL(16,"验证码已过期，请重新获取" ),
    FILE_UPLOAD_ERROR(17,"文件上传失败"),
    TAG_IS_ALREADY_EXIST(18,"标签已存在" ),
    TAG_NOT_EXIST(19,"标签不存在" ),
    DEFAULT_GROUP_CAN_NOT_DELETE(20,"默认分组不能删除" ),
    TYPE_IS_ALREADY_EXIST(21,"分类已经存在" ),
    TYPE_NOT_EXIST(22,"分类不存在" );
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
