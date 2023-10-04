package com.by.blog.service.impl;

import com.by.blog.dao.VisitorMapper;
import com.by.blog.entity.Visitor;
import com.by.blog.redis.RedisService;
import com.by.blog.redis.key.IpKey;
import com.by.blog.redis.key.UpdateTimeKey;
import com.by.blog.service.VisitorService;
import com.by.blog.util.IpUtil;
import com.by.blog.util.ResultVOUtil;
import com.by.blog.util.TimeUtil;
import com.by.blog.vo.IndexMsgVO;
import com.by.blog.vo.ResultVO;
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
            if (visitorMapper.updateByPrimaryKey(visitor) == 1) {
                redisService.set(IpKey.ipKey, Ip, visitor);
            }

        }
    }

    @Override
    public ResultVO getIndexMsg() {
        IndexMsgVO vo = new IndexMsgVO();
        vo.setArticleTotal(visitorMapper.getArticleTotal());
        vo.setCommentTotal(visitorMapper.getCommentTotal() + visitorMapper.getLeaveMsgTotal());
        vo.setVisitorTotal(visitorMapper.getVisitorTotal());
        vo.setTagTotal(visitorMapper.getTagsTotal());
        String updateTime = redisService.get(UpdateTimeKey.timeKey, "updateTime", String.class);
        if (updateTime == null || "".equals(updateTime)) {
            String time = TimeUtil.getTimeCN();
            redisService.set(UpdateTimeKey.timeKey, "updateTime", time);
        }
        vo.setUpdateTime(redisService.get(UpdateTimeKey.timeKey, "updateTime", String.class));
        return ResultVOUtil.success(vo);
    }
}
