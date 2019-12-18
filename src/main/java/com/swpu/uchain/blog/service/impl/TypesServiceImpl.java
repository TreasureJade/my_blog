package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.ArticleMapper;
import com.swpu.uchain.blog.dao.TypesMapper;
import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.entity.Types;
import com.swpu.uchain.blog.enums.DefaultEnum;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.form.InsertTypesForm;
import com.swpu.uchain.blog.form.UpdateTypesForm;
import com.swpu.uchain.blog.service.ArticleService;
import com.swpu.uchain.blog.service.TypesService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.TimeUtil;
import com.swpu.uchain.blog.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    private TypesMapper typesMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public ResultVO insertTypes(InsertTypesForm form) {
        if (typesMapper.selectByTypeMsg(form.getTypeMsg()) != null) {
            return ResultVOUtil.error(ResultEnum.TYPE_IS_ALREADY_EXIST);
        }
        Types types = new Types();
        BeanUtils.copyProperties(form, types);
        types.setCreatTime(TimeUtil.getNowTime());
        if (typesMapper.insert(types) == 1) {
            return ResultVOUtil.success(types);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO updateTypes(UpdateTypesForm form) {
        if (typesMapper.selectByPrimaryKey(form.getId()) == null) {
            return ResultVOUtil.error(ResultEnum.TYPE_NOT_EXIST);
        }
        Types types = new Types();
        BeanUtils.copyProperties(form, types);
        if (typesMapper.updateByPrimaryKey(types) == 1) {
            return ResultVOUtil.success(types);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO deleteTypes(Integer id) {
        if (id.equals(DefaultEnum.TYPE_DEFAULT_ENUM.getValue())) {
            return ResultVOUtil.error(ResultEnum.DEFAULT_GROUP_CAN_NOT_DELETE);
        }
        if (typesMapper.selectByPrimaryKey(id) == null) {
            return ResultVOUtil.error(ResultEnum.TYPE_NOT_EXIST);
        }
        List<Article> articles = articleMapper.selectByTypeId(id);
        if (typesMapper.deleteByPrimaryKey(id) == 1) {
            for (Article article : articles) {
                article.setTypeId(DefaultEnum.TYPE_DEFAULT_ENUM.getValue());
                articleService.update(article);
            }
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO selectAllTypes() {
        List<Types> types = typesMapper.selectAll();
        return ResultVOUtil.success(types);
    }
}
