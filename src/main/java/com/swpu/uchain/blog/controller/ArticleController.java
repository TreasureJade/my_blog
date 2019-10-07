package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.form.CreatArticleForm;
import com.swpu.uchain.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName ArticleController
 * @Author hobo
 * @Date 19-4-23 下午1:52
 * @Description
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "文章管理接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("获取文章详情")
    @PostMapping(name = "获取文章详情", value = "/getDetail")
    public Object getDetail(Long blogId) {
        return articleService.selectArticleDetail(blogId);
    }

    @ApiOperation("上传文章")
    @PostMapping(name = "上传文章", value = "/insertArticle")
    public Object insertArticle(@Valid CreatArticleForm form) {
        return articleService.insertArticle(form);
    }

    @ApiOperation("删除文章")
    @GetMapping(name = "删除文章", value = "/deleteArticle")
    public void deleteArticle() {
    }

    @PostMapping(name = "更改文章", value = "/updateArticle")
    public void updateArticle() {
    }

    @PostMapping(name = "获得所有文章", value = "/selectAll")
    public void selectAllArticle() {
    }

    @PostMapping(name = "根据标签查询文章", value = "/selectArticleByTags")
    public void selectArticleByTags() {
    }

    @PostMapping(name = "根据种类查询文章", value = "/selectArticleByTypes")
    public void selectArticleByTypes() {
    }
}
