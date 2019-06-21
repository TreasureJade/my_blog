package com.swpu.uchain.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Tags implements Serializable {
    private Integer id;

    private String tagsMsg;

    private Date creatTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagsMsg() {
        return tagsMsg;
    }

    public void setTagsMsg(String tagsMsg) {
        this.tagsMsg = tagsMsg == null ? null : tagsMsg.trim();
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
        sb.append(", tagsMsg=").append(tagsMsg);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}