package com.swpu.uchain.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {
    private Long id;

    private Long commentId;

    private Long blogId;

    private Long commentUserId;

    private String replyMsg;

    private Date creatTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg == null ? null : replyMsg.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commentId=").append(commentId);
        sb.append(", blogId=").append(blogId);
        sb.append(", commentUserId=").append(commentUserId);
        sb.append(", replyMsg=").append(replyMsg);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}