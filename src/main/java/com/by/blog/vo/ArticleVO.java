package com.by.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class ArticleVO {

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
     * 标签id
     */
    private Integer tagsId;


    /**
     * 标签
     */
    private String tag;

    /**
     * 分类id
     */
    private Integer typeId;

    /**
     * 类型
     */
    private String type;

    /**
     * 创建时间
     */
    private String creatTime;

    /**
     * 文章主体
     */
    private String article;

    /**
     * 是否点过赞
     */
    private Boolean isLike;

    /**
     * 文章所有评论
     */
    private List<CommentVO> commentList;



}
