package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.Visitor;
import com.swpu.uchain.blog.vo.IndexMsgVO;

import java.util.List;

public interface VisitorMapper {

    int updateByPrimaryKey(Visitor record);

    Visitor selectByPage(String page);

    int getArticleTotal();

    int getTagsTotal();

    int getCommentTotal();

    int getLeaveMsgTotal();
}