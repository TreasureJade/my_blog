package com.swpu.uchain.blog.redis.key;

/**
 * @ClassName ArticleKey
 * @Author hobo
 * @Date 19-4-23 下午7:08
 * @Description
 **/
public class ArticleKey extends BasePrefix {
    public ArticleKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static ArticleKey articleKey = new  ArticleKey(400,"title");
}
