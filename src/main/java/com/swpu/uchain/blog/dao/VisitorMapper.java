package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.Visitor;
import java.util.List;

public interface VisitorMapper {

    int updateByPrimaryKey(Visitor record);

    Visitor selectByPage(String page);
}