package com.by.blog.service.impl;

import com.by.blog.dao.ArticleMapper;
import com.by.blog.dao.TagsMapper;
import com.by.blog.entity.Article;
import com.by.blog.entity.Tags;
import com.by.blog.enums.DefaultEnum;
import com.by.blog.enums.ResultEnum;
import com.by.blog.form.InsertTagsForm;
import com.by.blog.form.UpdateTagsForm;
import com.by.blog.service.ArticleService;
import com.by.blog.service.TagsService;
import com.by.blog.util.ResultVOUtil;
import com.by.blog.util.TimeUtil;
import com.by.blog.vo.ResultVO;
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

    @Autowired
    private ArticleService articleService;


    @Override
    public ResultVO insertTags(InsertTagsForm form) {
        if (tagsMapper.selectByTagMsg(form.getTagsMsg()) != null) {
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
            articleService.update(article);
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
