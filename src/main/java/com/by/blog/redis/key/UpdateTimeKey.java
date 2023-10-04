package com.by.blog.redis.key;

/**
 * @author hobo
 * @description
 */
public class UpdateTimeKey extends BasePrefix{
    public UpdateTimeKey(String prefix) {
        super(prefix);
    }

    public static UpdateTimeKey timeKey = new UpdateTimeKey("updateTime");
}
