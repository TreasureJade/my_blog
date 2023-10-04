package com.by.blog.service.impl;

import com.by.blog.dao.LeaveMessageMapper;
import com.by.blog.dao.VisitorMapper;
import com.by.blog.entity.LeaveMessage;
import com.by.blog.entity.User;
import com.by.blog.enums.ResultEnum;
import com.by.blog.form.CreatLeaveMsgForm;
import com.by.blog.service.LeaveMessageService;
import com.by.blog.service.UserService;
import com.by.blog.util.ResultVOUtil;
import com.by.blog.util.TimeUtil;
import com.by.blog.vo.LeaveMsgListVO;
import com.by.blog.vo.LeaveMsgVO;
import com.by.blog.vo.ReplyVO;
import com.by.blog.vo.ResultVO;
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

    @Autowired
    private VisitorMapper visitorMapper;


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
        LeaveMsgListVO result = new LeaveMsgListVO();
        List<LeaveMsgVO> leaveMsgVOS = new ArrayList<>();
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
            leaveMsgVOS.add(vo);
        }

        result.setMsgVOList(leaveMsgVOS);
        result.setLeaveMsgNum(visitorMapper.getLeaveMsgTotal());
        return ResultVOUtil.success(result);
    }
}
