package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.form.*;
import com.swpu.uchain.blog.service.ArticleService;
import com.swpu.uchain.blog.util.IpUtil;
import com.swpu.uchain.blog.util.RandomUtil;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.UploadFileUtil;
import com.swpu.uchain.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ArticleController
 * @Author hobo
 * @Date 19-4-23 下午1:52
 * @Description
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "文章管理接口")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    private static String uploadPath = "/home/hobo/blog/blog-pic/";

    @ApiOperation("获取文章详情")
    @GetMapping(name = "获取文章详情", value = "/getDetail")
    public Object getDetail(Long blogId) {
        return articleService.selectArticleDetail(blogId);
    }

    @ApiOperation("上传文章")
    @PostMapping(name = "上传文章", value = "/insertArticle")
    public Object insertArticle(@Valid CreatArticleForm form) {
        return articleService.insertArticle(form);
    }

    @ApiOperation("上传图片")
    @PostMapping(name = "上传图片", value = "/uploadpic")
    public Object uploadFile(MultipartFile upload) {
        Map map = new HashMap();
        String fileName = upload.getOriginalFilename();

        assert fileName != null;
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = RandomUtil.getRandomStringByLength(10) + "." + suffix;
        String URL = IpUtil.getHostIp();
        String filePath = UploadFileUtil.uploadFile(uploadPath + newFileName, upload);
        if (!"".equals(filePath)) {
            map.put("uploaded", 1);
            map.put("fileName", newFileName);
            map.put("url", "http://" + URL + filePath);
            return map;
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @ApiOperation("删除文章")
    @GetMapping(name = "删除文章", value = "/deleteArticle")
    public Object deleteArticle(Long blogId) {
        return articleService.deleteArticle(blogId);
    }

    @ApiOperation("更新文章")
    @PostMapping(name = "更改文章", value = "/updateArticle")
    public Object updateArticle(@Valid UpdateArticleForm form) {
        return articleService.updateArticle(form);
    }

    @ApiOperation("获取所有文章")
    @GetMapping(name = "获得所有文章", value = "/selectAll")
    public Object selectAllArticle(PageForm form) {
        return articleService.selectAll(form.getPageNum(), form.getPageSize());
    }

    @ApiOperation("根据标签查询文章")
    @GetMapping(name = "根据标签查询文章", value = "/selectArticleByTags")
    public Object selectArticleByTags(SelectByTagForm form) {
        return articleService.selectArticleByTags(form);
    }

    @ApiOperation("根据种类查询文章")
    @GetMapping(name = "根据种类查询文章", value = "/selectArticleByTypes")
    public Object selectArticleByTypes(SelectByTypeForm form) {
        return articleService.selectArticleByTypes(form);
    }

    @ApiOperation("点赞或取消点赞文章")
    @GetMapping(name = "点赞或取消点赞文章",value = "/giveLike")
    public Object giveLike(GiveLikeForm form){
        return articleService.likeArticle(form.getBlogId(),form.getIsLike());
    }
}
