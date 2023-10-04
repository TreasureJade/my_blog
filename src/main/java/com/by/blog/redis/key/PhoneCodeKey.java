package com.by.blog.redis.key;

/**
 * @author hobo
 * @description
 */
public class PhoneCodeKey extends BasePrefix {
    public PhoneCodeKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static PhoneCodeKey phoneCodeKey = new PhoneCodeKey(300, "phoneNumber");
}
