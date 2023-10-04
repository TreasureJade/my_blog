package com.by.blog.dao;

import com.by.blog.entity.Tags;

import java.util.List;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tags record);

    Tags selectByPrimaryKey(Integer id);

    List<Tags> selectAll();

    int updateByPrimaryKey(Tags record);

    Tags selectByTagMsg(String tagsMsg);
}