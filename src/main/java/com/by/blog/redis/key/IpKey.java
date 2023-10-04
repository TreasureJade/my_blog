package com.by.blog.redis.key;

/**
 * @author hobo
 * @description
 */
public class IpKey extends BasePrefix{
    public IpKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static IpKey ipKey = new IpKey(300,"Ip");
}
