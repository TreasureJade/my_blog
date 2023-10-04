package com.by.blog.dto;

import com.by.blog.entity.Tags;
import com.by.blog.entity.Types;
import com.by.blog.vo.CommentVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class ArticleDTO {

    private Long id;

    /**
     * 作者
     */
    private String author;

    /**
     * 标题
     */
    private String title;

    /**
     * 阅读数
     */
    private Long reading;

    /**
     * 评论数
     */
    private Long comments;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 标签
     */
    private Tags tags;

    /**
     * 类型
     */
    private Types types;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 文章主体
     */
    private String article;

    /**
     * 文章所有评论
     */
    private List<CommentVO> commentList;
}
