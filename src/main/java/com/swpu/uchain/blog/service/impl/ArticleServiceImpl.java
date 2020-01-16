package com.swpu.uchain.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swpu.uchain.blog.dao.ArticleMapper;
import com.swpu.uchain.blog.dao.CommentMapper;
import com.swpu.uchain.blog.dao.UserLikesMapper;
import com.swpu.uchain.blog.entity.Article;
import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.entity.UserLikes;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.exception.GlobalException;
import com.swpu.uchain.blog.form.CreatArticleForm;
import com.swpu.uchain.blog.form.SelectByTagForm;
import com.swpu.uchain.blog.form.SelectByTypeForm;
import com.swpu.uchain.blog.form.UpdateArticleForm;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.ArticleKey;
import com.swpu.uchain.blog.service.ArticleService;
import com.swpu.uchain.blog.service.CommentService;
import com.swpu.uchain.blog.service.UserService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.TimeUtil;
import com.swpu.uchain.blog.vo.ArticleListVO;
import com.swpu.uchain.blog.vo.ArticleVO;
import com.swpu.uchain.blog.vo.CommentVO;
import com.swpu.uchain.blog.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ArticleServiceImpl
 * @Author hobo
 * @Date 19-4-23 下午6:45
 * @Description
 **/
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserLikesMapper likesMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;


    @Override
    public boolean insert(Article article) {
        if (articleMapper.insert(article) == 1) {
            redisService.set(ArticleKey.articleKey, article.getTitle(), article);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Article article) {
        if (articleMapper.updateByPrimaryKey(article) == 1) {
            redisService.set(ArticleKey.articleKey, article.getTitle(), article);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        redisService.delete(ArticleKey.articleKey, id + "");
        return (articleMapper.deleteByPrimaryKey(id) == 1);
    }

    public Article findArticleByTitle(String title) {
        return articleMapper.selectByArticleTitle(title);
    }

    @Override
    public ResultVO insertArticle(CreatArticleForm form) {
        Article article = new Article();
        BeanUtils.copyProperties(form, article);
        if (findArticleByTitle(article.getTitle()) != null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_TITLE_EXIST);
        }
        article.setCreatTime(TimeUtil.getTimeCN());
        if (insert(article)) {
            return ResultVOUtil.success(article);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO updateArticle(UpdateArticleForm form) {
        Article article = articleMapper.selectByPrimaryKey(form.getId());
        if (article == null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }
        BeanUtils.copyProperties(form, article);
        String updateTime = TimeUtil.getTimeCN();
        article.setUpdateTime(updateTime);
        if (update(article)) {
            return ResultVOUtil.success(article);
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO deleteArticle(Long id) {
        if (articleMapper.selectByPrimaryKey(id) == null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }
        if (delete(id)) {
            List<Long> commentIdList = commentMapper.getCommentIdByBlogId(id);
            for (Long commentId : commentIdList) {
                commentMapper.deleteByPrimaryKey(commentId);
            }
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListVO> list = articleMapper.getArticleList();
        PageInfo<ArticleListVO> result = new PageInfo<>(list);
        return ResultVOUtil.success(result);
    }

    @Override
    public void addReading(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new GlobalException(ResultEnum.ARTICLE_NOT_EXIST);
        }
        article.setReading(article.getReading() + 1);
        if (!update(article)) {
            throw new GlobalException(ResultEnum.ADD_READINGS_ERROR);
        }
    }

    @Override
    public ResultVO selectArticleDetail(Long blogId) {
        ArticleVO article = articleMapper.selectByArticleId(blogId);
        if (article == null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_NOT_EXIST);
        }
        // 阅读量 +1
        addReading(blogId);
        User user = userService.getCurrentUser();
        if (user != null) {
            UserLikes userLikes = likesMapper.selectByPrimaryKey(user.getUserId(), blogId);
            if (userLikes != null) {
                article.setIsLike(userLikes.getIsLike());
            } else {
                article.setIsLike(false);
            }
        }else {
            article.setIsLike(false);
        }
        List<CommentVO> commentList = commentService.getAllCommentByBlogId(blogId);
        article.setCommentList(commentList);

        return ResultVOUtil.success(article);
    }


    @Override
    public ResultVO selectArticleByTags(SelectByTagForm form) {
        List<ArticleListVO> list = articleMapper.selectArticlesByTagId(form.getTagId());
        return ResultVOUtil.success(list);
    }

    @Override
    public ResultVO selectArticleByTypes(SelectByTypeForm form) {
        List<ArticleListVO> list = articleMapper.selectByArticlesByTypeId(form.getTypeId());
        return ResultVOUtil.success(list);
    }

    @Override
    public ResultVO likeArticle(long blogId, boolean isLike) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        UserLikes userLikes = likesMapper.selectByPrimaryKey(user.getUserId(), blogId);
        // 点赞
        if (isLike) {
            if (userLikes == null) {
                UserLikes like = new UserLikes();
                like.setUserId(user.getUserId());
                like.setBlogId(blogId);
                like.setIsLike(isLike);
                if (likesMapper.insert(like) == 1) {
                    if (updateLike(blogId, isLike)) {
                        return ResultVOUtil.success();
                    }
                }
            } else {
                userLikes.setIsLike(isLike);
                if (likesMapper.updateByPrimaryKey(userLikes) == 1) {
                    if (updateLike(blogId, isLike)) {
                        return ResultVOUtil.success();
                    }
                }
            }
        } else {
            userLikes.setIsLike(isLike);
            if (likesMapper.updateByPrimaryKey(userLikes) == 1) {
                if (updateLike(blogId, isLike)) {
                    return ResultVOUtil.success();
                }
            }
        }

        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    private boolean updateLike(long blogId, boolean isLike) {
        Article article = articleMapper.selectByPrimaryKey(blogId);
        if (article != null) {
            if (isLike) {
                article.setLikes(article.getLikes() + 1);
                return update(article);
            } else {
                article.setLikes(article.getLikes() - 1);
                return update(article);
            }
        }
        return false;
    }

}
