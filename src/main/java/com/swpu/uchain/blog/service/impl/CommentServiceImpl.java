package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.ArticleMapper;
import com.swpu.uchain.blog.dao.CommentMapper;
import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.entity.Comment;
import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.form.CreatCommentForm;
import com.swpu.uchain.blog.service.CommentService;
import com.swpu.uchain.blog.service.UserService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.TimeUtil;
import com.swpu.uchain.blog.vo.CommentVO;
import com.swpu.uchain.blog.vo.ReplyVO;
import com.swpu.uchain.blog.vo.ResultVO;
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
