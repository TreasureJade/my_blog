package com.by.blog.dao;

import com.by.blog.entity.Visitor;


public interface VisitorMapper {

    int updateByPrimaryKey(Visitor record);

    Visitor selectByPage(String page);

    int getArticleTotal();

    int getTagsTotal();

    int getCommentTotal();

    int getLeaveMsgTotal();

    int getVisitorTotal();
}