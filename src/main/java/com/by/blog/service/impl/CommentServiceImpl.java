package com.by.blog.service.impl;

import com.by.blog.dao.ArticleMapper;
import com.by.blog.dao.CommentMapper;
import com.by.blog.entity.Article;
import com.by.blog.entity.Comment;
import com.by.blog.entity.User;
import com.by.blog.enums.ResultEnum;
import com.by.blog.form.CreatCommentForm;
import com.by.blog.service.CommentService;
import com.by.blog.service.UserService;
import com.by.blog.util.ResultVOUtil;
import com.by.blog.util.TimeUtil;
import com.by.blog.vo.CommentVO;
import com.by.blog.vo.ReplyVO;
import com.by.blog.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hobo
 * @description
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment) == 1;
    }

    @Override
    public boolean delete(Long id) {
        return commentMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public ResultVO creatComment(CreatCommentForm form) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(form, comment);
        if (comment.getReplyUserId() == null) {
            comment.setReplyUserId(0L);
        }
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        comment.setUserId(user.getUserId());
        comment.setCreatTime(TimeUtil.getNowTime());

        if (insert(comment)) {
            // 评论数目加1
            addComments(comment.getBlogId());
            return ResultVOUtil.success(comment);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    private void addComments(Long blogId) {
        Article article = articleMapper.selectByPrimaryKey(blogId);
        article.setComments(article.getComments() + 1);
        articleMapper.updateByPrimaryKey(article);
    }

    @Override
    public List<CommentVO> getAllCommentByBlogId(Long blogId) {
        List<CommentVO> result = new ArrayList<>();
        // 查找文章下所有的父级评论
        List<Long> commentIdList = commentMapper.getCommentIdByBlogId(blogId);
        for (Long commentId : commentIdList) {
            CommentVO vo = commentMapper.selectCommentById(commentId);
            //查找所有父级评论下的子评论
            List<ReplyVO> vos = commentMapper.selectByPid(vo.getCommentId());
            List<ReplyVO> reply = new ArrayList<>();
            for (ReplyVO replyVO : vos) {
                User user = userService.selectByUserId(replyVO.getReplyUserId());
                replyVO.setReplyUserName(user.getUsername());
                reply.add(replyVO);
            }
            vo.setReplyVO(reply);
            result.add(vo);
        }
        return result;
    }


}
