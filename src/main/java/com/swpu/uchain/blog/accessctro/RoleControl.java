package com.swpu.uchain.blog.accessctro;

import com.swpu.uchain.blog.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName RoleControl
 * @Author hobo
 * @Date 19-4-22 下午7:45
 * @Description
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleControl {
    RoleEnum role();
}
