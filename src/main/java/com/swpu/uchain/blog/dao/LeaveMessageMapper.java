package com.swpu.uchain.blog.dao;

import com.swpu.uchain.blog.entity.LeaveMessage;
import com.swpu.uchain.blog.vo.LeaveMsgVO;
import com.swpu.uchain.blog.vo.ReplyVO;

import java.util.List;

public interface LeaveMessageMapper {
    int deleteByPrimaryKey(Long leaveMessageId);

    int insert(LeaveMessage record);

    LeaveMessage selectByPrimaryKey(Long leaveMessageId);

    List<LeaveMessage> selectAll();

    int updateByPrimaryKey(LeaveMessage record);

    List<Long> getAllParentLeaveMsg();

    LeaveMsgVO selectByLeaveMsgId(Long leaveMsgId);

    List<ReplyVO> selectByPid(Long leaveMessageId);
}