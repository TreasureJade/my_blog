package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.CommentMapper;
import com.swpu.uchain.blog.dao.ReplyMapper;
import com.swpu.uchain.blog.entity.Comment;
import com.swpu.uchain.blog.entity.Reply;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.CommentKey;
import com.swpu.uchain.blog.service.CommentService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Author hobo
 * @Date 19-5-1 下午4:55
 * @Description
 **/
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean insert(Comment comment) {
        if (commentMapper.insert(comment) == 1) {
            redisService.set(CommentKey.commentKey, comment.getId() + "", comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        redisService.delete(CommentKey.commentKey, id + "");
        return (commentMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public boolean insertReply(Reply reply) {
        if (replyMapper.insert(reply) == 1) {
            redisService.set(CommentKey.replyKey, reply.getId() + "", reply);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReply(Long id) {
        redisService.delete(CommentKey.replyKey, id + "");
        return (replyMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public ResultVO insertComment(Comment comment) {
        if (insert(comment)) {
            return ResultVOUtil.success(comment);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO deleteComment(Long id) {
        if (delete(id)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO getCommentAndReplyList() {
        //TODO 将所有的评论和回复拼装起来返回
        return null;
    }

    List<Comment> comments() {
        return commentMapper.selectAll();
    }

    List<Reply> replies() {
        return replyMapper.selectAll();
    }
}
