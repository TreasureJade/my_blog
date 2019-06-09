package com.swpu.uchain.blog.util;

import com.aliyuncs.IAcsClient;
import com.swpu.uchain.blog.config.AccessTokenProperty;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云短信验证工具类
 *
 * @author hobo
 */
@Slf4j
public class SendMsgUtil {

    private AccessTokenProperty accessTokenProperty;

    /**
     * 发送短信客户端
     */
    private static IAcsClient acsClient;

    /**
     * 产品名称
     */
    private static final String PRODUCT = "Dusmapi";

    /**
     * 产品域名
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    private static final String SIGN_NAME = "hobo";

    private String accessKeyId = accessTokenProperty.getAccessKeyId();
    private String accessKeySecret = accessTokenProperty.getAccessKeySecret();

    private static String defaultConnectTimeout = System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
    private static String defaultReadTimeout = System.setProperty("sun.net.client.defaultReadTimeout", "10000");

    

}
