package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.vo.ResultVO;

public interface ArticleService {

    /**
     * 添加
     *
     * @param article
     * @return boolean
     * @author hobo
     */
    boolean insert(Article article);

    /**
     * 更新
     *
     * @param article
     * @return boolean
     * @author hobo
     */
    boolean update(Article article);

    /**
     * 删除
     *
     * @param id
     * @return boolean
     * @author hobo
     */
    boolean delete(Long id);

    /**
     * 上传一篇博客
     *
     * @param article
     * @return java.lang.Object
     * @author hobo
     */
    ResultVO insertArticle(Article article);

    /**
     * 修改一篇博客
     *
     * @param article
     * @author hobo
     * @return com.swpu.uchain.blog.vo.ResultVO
     */
    ResultVO updateArticle(Article article);

    /**
     * 删除一篇博客
     *
     * @param article
     * @return
     */
    ResultVO deleteArticle(Article article);

}
