package com.swpu.uchain.blog.redis.key;

import com.swpu.uchain.blog.redis.KeyPrefix;

/**
 * @ClassName BasePrefix
 * @Author hobo
 * @Date 19-4-23 下午6:53
 * @Description
 **/
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return this.expireSeconds;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }
}
