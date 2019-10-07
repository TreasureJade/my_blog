package com.swpu.uchain.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hobo
 * @description
 */
@Component
@ConfigurationProperties("redis")
@Data
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
    private String password;
}
