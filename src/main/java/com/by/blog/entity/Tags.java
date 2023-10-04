package com.by.blog.entity;

import java.io.Serializable;

public class Tags implements Serializable {
    private Integer id;

    private String tagsMsg;

    private String creatTime;

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

    public String  getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String  creatTime) {
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