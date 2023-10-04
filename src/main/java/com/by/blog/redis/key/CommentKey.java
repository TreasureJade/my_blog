package com.by.blog.redis.key;

/**
 * @ClassName CommentKey
 * @Author hobo
 * @Date 19-5-1 下午6:42
 * @Description
 **/
public class CommentKey extends BasePrefix{
    public CommentKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static CommentKey commentKey = new CommentKey(360,"blogId");
}
