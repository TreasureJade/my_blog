package com.by.blog.util;

import java.util.Random;

/**
 * @ClassName RandomUtil
 * @Author hobo
 * @Date 19-3-4 下午12:52
 * @Description
 **/
public class RandomUtil {

    /**
     * @return java.lang.String
     * @Author hobo
     * @Description : 获取一定长度的字符串，范围0~9，a-z
     * @Param [length] 指定字符串长度
     **/
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(base.length());
            sb.append(base.charAt(num));
        }
        return sb.toString();
    }

    /**
     * 创建验证码
     * @return java.lang.String
     */
    public static String setCode() {
        int code = (int) ((Math.random() * 999999) + 100);
        return code + "";
    }
}
