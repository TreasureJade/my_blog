package com.by.blog.dao;

import com.by.blog.entity.Article;
import com.by.blog.vo.ArticleListVO;
import com.by.blog.vo.ArticleVO;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    Article selectByArticleTitle(String title);

    ArticleVO selectByArticleId(Long id);

    List<Article> selectByTagsId(Integer tagsId);

    List<Article> selectByTypeId(Integer typeId);

    List<ArticleListVO> getArticleList();

    List<ArticleListVO> selectArticlesByTagId(Integer tagId);

    List<ArticleListVO> selectByArticlesByTypeId(Integer typeId);
}