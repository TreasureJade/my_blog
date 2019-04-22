package com.swpu.uchain.blog.enums;

import lombok.Getter;

import java.util.HashMap;

/**
 * @ClassName RoleEnum
 * @Author hobo
 * @Date 19-4-22 下午7:29
 * @Description
 **/

@Getter
public enum RoleEnum {
    TOURISTS(0, "游客"),
    USER(1, "用户"),
    ADMIN(2, "管理员"),
    ;


    private Integer value;
    private String role;

    RoleEnum(Integer value, String role) {
        this.value = value;
        this.role = role;
    }

    public static String getRole(Integer integer) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(ADMIN.getValue(), ADMIN.getRole());
        hashMap.put(USER.getValue(), USER.getRole());
        hashMap.put(TOURISTS.getValue(), TOURISTS.getRole());
        return hashMap.get(integer);
    }

    public static Integer getValue(String role) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(ADMIN.getRole(), ADMIN.getValue());
        hashMap.put(USER.getRole(), USER.getValue());
        hashMap.put(TOURISTS.getRole(), TOURISTS.getValue());
        return hashMap.get(role);
    }
}
