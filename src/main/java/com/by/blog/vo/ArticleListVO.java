package com.by.blog.vo;

import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class ArticleListVO {

    private Long blogId;

    private String digest;

    private String author;

    private String title;

    private String creatTime;

    private Integer tagsId;

    private String tagsMsg;

    private Integer typeId;

    private String typeMsg;

    private Integer commentSum;

}
