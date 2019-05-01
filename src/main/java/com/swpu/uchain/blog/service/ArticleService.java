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
     * @return com.swpu.uchain.blog.vo.ResultVO
     * @author hobo
     */
    ResultVO updateArticle(Article article);

    /**
     * 删除一篇博客
     *
     * @param id
     * @return
     */
    ResultVO deleteArticle(Long id);

    /**
     * 分页显示博客
     *
     * @param pageNum
     * @param pageSize
     * @author hobo
     * @return com.swpu.uchain.blog.vo.ResultVO
     */
    ResultVO selectAll(int pageNum, int pageSize);

    /**
     * 阅读量递增
     * @param id
     * @author hobo
     */
    void addReading(Long id);
}
