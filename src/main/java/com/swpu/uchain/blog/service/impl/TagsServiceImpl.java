package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.ArticleMapper;
import com.swpu.uchain.blog.dao.TagsMapper;
import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.entity.Tags;
import com.swpu.uchain.blog.enums.DefaultEnum;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.form.InsertTagsForm;
import com.swpu.uchain.blog.form.UpdateTagsForm;
import com.swpu.uchain.blog.service.TagsService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.TimeUtil;
import com.swpu.uchain.blog.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Service
@Slf4j
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public ResultVO insertTags(InsertTagsForm form) {
        if (tagsMapper.selectByTag(form.getTagsMsg()) != null) {
            return ResultVOUtil.error(ResultEnum.TAG_IS_ALREADY_EXIST);
        }
        Tags tags = new Tags();
        BeanUtils.copyProperties(form, tags);
        tags.setCreatTime(TimeUtil.getNowTime());
        if (tagsMapper.insert(tags) == 1) {
            return ResultVOUtil.success(tags);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO updateTags(UpdateTagsForm form) {
        if (tagsMapper.selectByPrimaryKey(form.getId()) == null) {
            return ResultVOUtil.error(ResultEnum.TAG_NOT_EXIST);
        }
        Tags tags = new Tags();
        BeanUtils.copyProperties(form, tags);
        if (tagsMapper.updateByPrimaryKey(tags) == 1) {
            return ResultVOUtil.success(tags);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO deleteTags(Integer id) {
        if (id.equals(DefaultEnum.TAGS_DEFAULT_ENUM.getValue())) {
            return ResultVOUtil.error(ResultEnum.DEFAULT_GROUP_CAN_NOT_DELETE);
        }
        if (tagsMapper.selectByPrimaryKey(id) == null) {
            return ResultVOUtil.error(ResultEnum.TAG_NOT_EXIST);
        }
        List<Article> articles = articleMapper.selectByTagsId(id);
        // 将要删除的标签下的所有文章放入默认分组
        for (Article article : articles) {
            article.setTagsId(DefaultEnum.TAGS_DEFAULT_ENUM.getValue());
        }
        if (tagsMapper.deleteByPrimaryKey(id) == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO selectAllTags() {
        List<Tags> tags = tagsMapper.selectAll();
        return ResultVOUtil.success(tags);
    }
}
