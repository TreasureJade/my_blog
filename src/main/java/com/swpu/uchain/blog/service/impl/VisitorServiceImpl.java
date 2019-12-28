package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.VisitorMapper;
import com.swpu.uchain.blog.entity.Visitor;
import com.swpu.uchain.blog.redis.RedisService;
import com.swpu.uchain.blog.redis.key.IpKey;
import com.swpu.uchain.blog.redis.key.UpdateTimeKey;
import com.swpu.uchain.blog.service.VisitorService;
import com.swpu.uchain.blog.util.IpUtil;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.TimeUtil;
import com.swpu.uchain.blog.vo.IndexMsgVO;
import com.swpu.uchain.blog.vo.ResultVO;
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
        vo.setCommentTotal(visitorMapper.getCommentTotal());
        vo.setLeaveMessageTotal(visitorMapper.getLeaveMsgTotal());
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
