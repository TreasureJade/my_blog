package com.swpu.uchain.blog.vo;

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

    private String tagsMsg;

    private String typeMsg;

    private Integer commentSum;

}
