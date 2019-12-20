package com.swpu.uchain.blog.service;

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

}
