package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.form.CreatArticleForm;
import com.swpu.uchain.blog.form.UpdateArticleForm;
import com.swpu.uchain.blog.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

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
     * @param tagId
     * @return
     */
    ResultVO selectArticleByTags(Integer tagId);

    /**
     * 根据种类查询文章
     *
     * @param typeId
     * @return
     */
    ResultVO selectArticleByTypes(Integer typeId);
}
