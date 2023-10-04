package com.by.blog.dao;

import com.by.blog.entity.LeaveMessage;
import com.by.blog.vo.LeaveMsgVO;
import com.by.blog.vo.ReplyVO;

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