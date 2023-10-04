package com.by.blog.entity;

import java.io.Serializable;

public class LeaveMessage implements Serializable {
    private Long leaveMessageId;

    private Long userId;

    private Long replyUserId;

    private Long pid;

    private String leaveMsg;

    private String creatTime;

    private static final long serialVersionUID = 1L;

    public Long getLeaveMessageId() {
        return leaveMessageId;
    }

    public void setLeaveMessageId(Long leaveMessageId) {
        this.leaveMessageId = leaveMessageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLeaveMsg() {
        return leaveMsg;
    }

    public void setLeaveMsg(String leaveMsg) {
        this.leaveMsg = leaveMsg == null ? null : leaveMsg.trim();
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", leaveMessageId=").append(leaveMessageId);
        sb.append(", userId=").append(userId);
        sb.append(", replyUserId=").append(replyUserId);
        sb.append(", pid=").append(pid);
        sb.append(", leaveMsg=").append(leaveMsg);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}