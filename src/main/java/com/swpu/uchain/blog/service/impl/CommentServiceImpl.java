package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.ArticleMapper;
import com.swpu.uchain.blog.dao.CommentMapper;
import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.entity.Comment;
import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.CommentKey;
import com.swpu.uchain.blog.service.ArticleService;
import com.swpu.uchain.blog.service.CommentService;
import com.swpu.uchain.blog.service.UserService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.TimeUtil;
import com.swpu.uchain.blog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author hobo
 * @description
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Override
    public boolean insert(Comment comment) {
        if (commentMapper.insert(comment) == 1) {
            redisService.set(CommentKey.commentKey, comment.getBlogId() + "", comment);
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long id) {
        redisService.delete(CommentKey.commentKey, id + "");
        return (commentMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public ResultVO creatComment(Long blogId, String commentMsg) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.AUTHENTICATION_ERROR);
        }
        Article article = articleMapper.selectByPrimaryKey(blogId);
        if (article == null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }
        Comment comment = new Comment();
        comment.setUserId(user.getId());
        comment.setBlogId(article.getId());
        comment.setCommentMsg(commentMsg);
        comment.setCreatTime(TimeUtil.getNowTime());
        if (insert(comment)) {
            return ResultVOUtil.success(comment);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO replyComment(Long blogId, Long commentId, Long replyId, String commentMsg) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.AUTHENTICATION_ERROR);
        }
        Article article = articleMapper.selectByPrimaryKey(blogId);
        if (article == null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }
        Comment comment = new Comment();
        comment.setBlogId(article.getId());
        comment.setCreatTime(TimeUtil.getNowTime());
        comment.setCommentMsg(commentMsg);
        if (replyId != null) {
            Comment replyComment = commentMapper.selectByPrimaryKey(replyId);
            comment.setUserId(user.getId());
            comment.setReplyUserId(replyComment.getUserId());
            comment.setPid(replyComment.getId());
        }
        if (insert(comment)) {
            return ResultVOUtil.success(comment);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }
}
