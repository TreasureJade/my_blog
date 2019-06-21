package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/list", name = "获取博客下所有评论")
    public void commentList() {
    }

    @PostMapping(value = "/delete", name = "删除评论")
    public void deleteComment() {
    }


    @GetMapping(value = "/addComments", name = "添加评论数")
    public void addComments() {
    }

    /**
     * commentId为空时，replyId不能为空
     * @param blogId 博客id
     * @param commentId 用于判断是评论还是回复 如果空就是评论
     * @param replyId 回复评论的commentId
     * @param commentMsg 评论/回复内容
     * @return ResultVO
     */
    @PostMapping(value = "/comment", name = "评论")
    public Object test(Long blogId, Long commentId, Long replyId, String commentMsg) {
        if (commentId == null) {
            return (commentService.creatComment(blogId, commentMsg));
        } else {
            return (commentService.replyComment(blogId, commentId, replyId, commentMsg));
        }
    }
}
