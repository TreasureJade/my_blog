package com.swpu.uchain.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "用户评论接口")
public class CommentController {

    @GetMapping(value = "/list", name = "获取博客下所有评论")
    public void commentList() {
    }

    @PostMapping(value = "/insert", name = "添加新评论")
    public void insertComment() {
    }

    @PostMapping(value = "/delete", name = "删除评论")
    public void deleteComment() {
    }

    @PostMapping(value = "/reply", name = "回复评论")
    public void replyComment() {
    }

    @GetMapping(value = "/addComment", name = "添加评论数")
    public void addComments() {
    }
}
