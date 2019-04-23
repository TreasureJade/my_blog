package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.Article;
import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    Article selectByArticleTitle(String title);
}