package com.by.blog.service;

import com.by.blog.entity.Article;
import com.by.blog.form.CreatArticleForm;
import com.by.blog.form.SelectByTagForm;
import com.by.blog.form.SelectByTypeForm;
import com.by.blog.form.UpdateArticleForm;
import com.by.blog.vo.ResultVO;

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
     * @param form
     * @return java.lang.Object
     * @author hobo
     */
    ResultVO insertArticle(CreatArticleForm form);

    /**
     * 修改一篇博客
     *
     * @param form
     * @return com.swpu.uchain.blog.vo.ResultVO
     * @author hobo
     */
    ResultVO updateArticle(UpdateArticleForm form);

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
     * @return com.swpu.uchain.blog.vo.ResultVO
     * @author hobo
     */
    ResultVO selectAll(int pageNum, int pageSize);

    /**
     * 阅读量递增
     *
     * @param id
     * @author hobo
     */
    void addReading(Long id);

    /**
     * 获取博客详情
     *
     * @return
     */
    ResultVO selectArticleDetail(Long blogId);


    /**
     * 根据标签查询文章
     *
     * @param form
     * @return
     */
    ResultVO selectArticleByTags(SelectByTagForm form);

    /**
     * 根据种类查询文章
     *
     * @param form
     * @return
     */
    ResultVO selectArticleByTypes(SelectByTypeForm form);

    /**
     * 点赞/取消点赞
     * @param blogId
     * @param isLike
     * @return
     */
    ResultVO likeArticle(long blogId,boolean isLike);
}
