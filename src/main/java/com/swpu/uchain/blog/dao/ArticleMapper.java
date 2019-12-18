package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.vo.ArticleListVO;
import com.swpu.uchain.blog.vo.ArticleVO;

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