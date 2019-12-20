package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.VisitorMapper;
import com.swpu.uchain.blog.entity.Visitor;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.IpKey;
import com.swpu.uchain.blog.service.VisitorService;
import com.swpu.uchain.blog.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hobo
 * @description
 */
@Service
public class VisitorServiceImpl implements VisitorService {


    @Autowired
    private VisitorMapper visitorMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void addVisitorNum(HttpServletRequest request) {
        String Ip = IpUtil.getClient(request);
        Visitor visitor = redisService.get(IpKey.ipKey, Ip, Visitor.class);
        if (visitor == null) {
            visitor = visitorMapper.selectByPage("total_page");
            visitor.setTotalNum(visitor.getTotalNum() + 1);
            if (visitorMapper.updateByPrimaryKey(visitor)==1){
                redisService.set(IpKey.ipKey,Ip,visitor);
            }

        }
    }
}
