package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.ArticleMapper;
import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.ArticleKey;
import com.swpu.uchain.blog.service.ArticleService;
import com.swpu.uchain.blog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ArticleServiceImpl
 * @Author hobo
 * @Date 19-4-23 下午6:45
 * @Description
 **/
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean insert(Article article) {
        if (articleMapper.insert(article) == 1) {
            redisService.set(ArticleKey.articleKey, article.getTitle(), article);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Article article) {
        if (articleMapper.updateByPrimaryKey(article) == 1) {
            redisService.set(ArticleKey.articleKey, article.getTitle(), article);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        redisService.delete(ArticleKey.articleKey, id + "");
        return (articleMapper.deleteByPrimaryKey(id) == 1);
    }

    public Article findArticleByTitle(String title){
        return articleMapper.selectByArticleTitle(title);
    }

    @Override
    public ResultVO insertArticle(Article article) {
        return null;
    }

    @Override
    public ResultVO updateArticle(Article article) {
        return null;
    }

    @Override
    public ResultVO deleteArticle(Article article) {
        return null;
    }
}
