package com.by.blog.redis;

public interface KeyPrefix {
    /**
     * 获取过期时间
     *
     */
    int expireSeconds();

    /**
     * 获取key前缀
     *
     */
    String getPrefix();
}
