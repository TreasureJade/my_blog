package com.by.blog.service;

import com.by.blog.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hobo
 * @description
 */
public interface VisitorService {

    /**
     * 增加网站访客量
     * @return
     */
    void addVisitorNum(HttpServletRequest request);

    ResultVO getIndexMsg();

}
