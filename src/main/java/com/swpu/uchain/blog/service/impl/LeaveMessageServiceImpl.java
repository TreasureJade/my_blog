package com.swpu.uchain.blog.service.impl;

import com.swpu.uchain.blog.dao.LeaveMessageMapper;
import com.swpu.uchain.blog.entity.LeaveMessage;
import com.swpu.uchain.blog.entity.User;
import com.swpu.uchain.blog.enums.ResultEnum;
import com.swpu.uchain.blog.form.CreatLeaveMsgForm;
import com.swpu.uchain.blog.service.LeaveMessageService;
import com.swpu.uchain.blog.service.UserService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import com.swpu.uchain.blog.util.TimeUtil;
import com.swpu.uchain.blog.vo.LeaveMsgVO;
import com.swpu.uchain.blog.vo.ReplyVO;
import com.swpu.uchain.blog.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hobo
 * @description
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageMapper messageMapper;

    @Autowired
    private UserService userService;


    @Override
    public ResultVO insertLeaveMsg(CreatLeaveMsgForm form) {
        LeaveMessage leaveMessage = new LeaveMessage();
        BeanUtils.copyProperties(form, leaveMessage);
        if (leaveMessage.getReplyUserId() == null) {
            leaveMessage.setReplyUserId(0L);
        }
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN);
        }
        leaveMessage.setUserId(user.getUserId());
        leaveMessage.setCreatTime(TimeUtil.getNowTime());
        if (messageMapper.insert(leaveMessage) == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO selectAllLeaveMsg() {
        List<LeaveMsgVO> result = new ArrayList<>();
        List<Long> leaveMsgIdList = messageMapper.getAllParentLeaveMsg();
        for (Long leaveMsgId : leaveMsgIdList) {
            LeaveMsgVO vo = messageMapper.selectByLeaveMsgId(leaveMsgId);
            List<ReplyVO> vos = messageMapper.selectByPid(vo.getLeaveMessageId());
            List<ReplyVO> reply = new ArrayList<>();
            for (ReplyVO replyVO : vos) {
                User user = userService.selectByUserId(replyVO.getReplyUserId());
                replyVO.setReplyUserName(user.getUsername());
                reply.add(replyVO);
            }
            vo.setReplyVO(reply);
            result.add(vo);
        }
        return ResultVOUtil.success(result);
    }
}
