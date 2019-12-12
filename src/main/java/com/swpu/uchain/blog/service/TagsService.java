package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.form.InsertTagsForm;
import com.swpu.uchain.blog.form.UpdateTagsForm;
import com.swpu.uchain.blog.vo.ResultVO;

/**
 * @author hobo
 * @description
 */
public interface TagsService {

    /**
     * 添加标签
     * @param form 添加标签表单
     * @return com.swpu.uchain.blog.vo.ResultVO
     */
    ResultVO insertTags(InsertTagsForm form);

    /**
     * 修改标签
     * @param form 修改标签表单
     * @return com.swpu.uchain.blog.vo.ResultVO
     */
    ResultVO updateTags(UpdateTagsForm form);

    /**
     * 删除标签
     * @param id 标签id
     * @return com.swpu.uchain.blog.vo.ResultVO
     */
    ResultVO deleteTags(Integer id);

    /**
     * 查看所有标签
     * @return com.swpu.uchain.blog.vo.ResultVO
     */
    ResultVO selectAllTags();
}
