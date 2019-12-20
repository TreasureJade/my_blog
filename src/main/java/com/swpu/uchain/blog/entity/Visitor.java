package com.swpu.uchain.blog.entity;

import java.io.Serializable;

public class Visitor implements Serializable {
    private Integer id;

    private String page;

    private Long totalNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page == null ? null : page.trim();
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", page=").append(page);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}