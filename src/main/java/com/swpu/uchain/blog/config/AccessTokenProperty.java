package com.swpu.uchain.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信签名
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AccessTokenProperty {

    private String accessKeyId;

    private String accessKeySecret;

    private String insertTemplate;

    private String updatePWTemplate;
}
