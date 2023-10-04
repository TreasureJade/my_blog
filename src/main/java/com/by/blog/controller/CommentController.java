package com.by.blog.controller;

import com.by.blog.form.CreatCommentForm;
import com.by.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CommentController
 * @Author hobo
 * @Date 19-5-1 下午4:44
 * @Description
 **/
@RestController
@RequestMapping("/comment")
@Api(tags = "用户评论接口  需要登录")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;


    @ApiOperation("评论文章")
    @PostMapping(name = "评论某篇文章",value = "/insert")
    public Object creatComment(CreatCommentForm form) {
        return commentService.creatComment(form);
    }
}
