package com.by.blog.vo;

import lombok.Data;

/**
 * @ClassName ResultVO
 * @Author hobo
 * @Date 19-4-22 下午3:39
 * @Description
 **/
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
